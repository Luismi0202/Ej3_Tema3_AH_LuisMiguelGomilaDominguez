import { Injectable, NgZone } from '@angular/core';
import { Geolocation, Position, PermissionStatus } from '@capacitor/geolocation';
import { BehaviorSubject, Observable } from 'rxjs';

// Creamos una interfaz para tipar las coordenadas
export interface Coords {
  lat: number;
  lng: number;
}

@Injectable({
  providedIn: 'root'
})
export class GpsService {
  // BehaviorSubject para almacenar la posición actual. Empieza como null.
  private positionSubject = new BehaviorSubject<Coords | null>(null);
  
  // Observable público para que los componentes se suscriban a los cambios de posición.
  public position$: Observable<Coords | null> = this.positionSubject.asObservable();
  
  // ID del watcher para poder detenerlo después
  private watchId: string | null = null;

  constructor(private zone: NgZone) {}

  /**
   * Solicita los permisos de geolocalización y, si se conceden,
   * inicia el seguimiento de la ubicación.
   */
  public async startTracking() {
    try {
      // Primero, verificamos y solicitamos permisos
      const permissions = await this.checkAndRequestPermissions();
      if (!permissions) {
        console.error('Permisos de geolocalización no concedidos.');
        // Opcional: podrías emitir un evento de error aquí
        return;
      }

      // Si ya hay un seguimiento activo, lo detenemos antes de empezar uno nuevo
      if (this.watchId) {
        await this.stopTracking();
      }

      // Iniciamos el seguimiento de la posición
      this.watchId = await Geolocation.watchPosition({
        enableHighAccuracy: true, // Máxima precisión
        timeout: 10000,           // Timeout de 10 segundos
        maximumAge: 0             // No usar caché de ubicación
      }, (position, err) => {
        if (err) {
          console.error('Error al observar la posición:', err);
          this.positionSubject.next(null); // Emitimos null en caso de error
          return;
        }

        if (!position) {
          console.error('Posición no disponible');
          this.positionSubject.next(null);
          return;
        }

        // Usamos NgZone para asegurarnos de que la actualización se propague correctamente en Angular
        this.zone.run(() => {
          const coords: Coords = {
            lat: position.coords.latitude,
            lng: position.coords.longitude
          };
          this.positionSubject.next(coords); // Emitimos las nuevas coordenadas
        });
      });

    } catch (error) {
      console.error('Error al iniciar el seguimiento de GPS:', error);
      this.positionSubject.next(null);
    }
  }

  /**
   * Detiene el seguimiento de la ubicación.
   */
  public async stopTracking() {
    if (this.watchId) {
      await Geolocation.clearWatch({ id: this.watchId });
      this.watchId = null; // Limpiamos el ID
      console.log('Seguimiento de GPS detenido.');
    }
  }

  /**
   * Comprueba los permisos y los solicita si no están concedidos.
   * @returns boolean - True si los permisos fueron concedidos.
   */
  private async checkAndRequestPermissions(): Promise<boolean> {
    let permissions: PermissionStatus;

    // Comprueba el estado actual del permiso
    permissions = await Geolocation.checkPermissions();
    console.log('Permisos actuales:', permissions.location);

    if (permissions.location === 'granted') {
      return true;
    }

    if (permissions.location === 'denied') {
      console.error('El usuario ha denegado los permisos de ubicación.');
      // Aquí podrías mostrar un mensaje al usuario para que los active manualmente
      return false;
    }

    // Si el permiso no está determinado ('prompt'), lo solicita
    permissions = await Geolocation.requestPermissions();
    return permissions.location === 'granted';
  }
}
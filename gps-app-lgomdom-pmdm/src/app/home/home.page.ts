import { Component, OnDestroy, OnInit } from '@angular/core';
import { IonHeader, IonToolbar, IonTitle, IonContent, IonButton, IonCard, IonCardHeader, IonCardTitle, IonCardContent, IonText } from '@ionic/angular/standalone';
import { GpsService, Coords } from '../services/gps.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: true,
  imports: [CommonModule, IonHeader, IonToolbar, IonTitle, IonContent, IonButton, IonCard, IonCardHeader, IonCardTitle, IonCardContent, IonText],
})
export class HomePage implements OnInit, OnDestroy {
  
  // Variable para almacenar las coordenadas actuales
  public currentLocation: Coords | null = null;
  public trackingActive = false;

  constructor(private gpsService: GpsService) {}

  ngOnInit() {
    // Nos suscribimos al observable de posición del servicio
    this.gpsService.position$.subscribe(coords => {
      this.currentLocation = coords;
      if (coords) {
        this.trackingActive = true;
      }
    });
  }

  // Método para iniciar el seguimiento
  startGpsTracking() {
    this.gpsService.startTracking();
  }

  // Método para detener el seguimiento
  stopGpsTracking() {
    this.gpsService.stopTracking();
    this.trackingActive = false;
    // Opcional: podrías querer limpiar la última ubicación conocida
    // this.currentLocation = null;
  }

  // Es una buena práctica detener el seguimiento cuando el componente se destruye
  ngOnDestroy() {
    this.gpsService.stopTracking();
  }
}
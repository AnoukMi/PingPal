import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'actionnable_mockup';

  // Initialise la variable currentRoute avec une valeur par défaut vide (qui prendra ensuite la valeur de la route actuelle)
  currentRoute: string = '';

  constructor(private router: Router) {}

  ngOnInit() {
    // Écoute les événements relatifs au routage
    this.router.events
      // Filtre uniquement les événements de type NavigationEnd (url pour naviguer d'une page à l'autre)
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(() => {
        // Met à jour la variable currentRoute avec l'URL actuelle dès que celle ci varie
        this.currentRoute = this.router.url;
      });
  }
}

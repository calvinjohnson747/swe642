import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
})
export class HomePageComponent implements OnInit {
  username: string | null = null;
  greeting: string = "";

  constructor(private router: Router) {}

  ngOnInit() {
    this.username = this.getCookie("username");

    const currentdt = new Date();
    const hour = currentdt.getHours();

    let userName;
    if (!this.username) { 
      userName = window.prompt("Enter your name:") || ""; // Use empty string if prompt returns null
      this.setCookie("username", userName, 1);
    } else {
      userName = this.username;
    }

    if (hour >= 5 && hour < 12) {
      this.greeting = "Good Morning ";
    } else if (hour >= 12 && hour < 16) {
      this.greeting = "Good Afternoon ";
    } else {
      this.greeting = "Good Evening ";
    }

    this.greeting += userName + ", welcome to SWE642 Survey!";
  }

  goToList() {
    this.router.navigate(['list']);
  }

  goToForm() {
    this.router.navigate(['survey']);
  }

  getCookie(name: string): string | null {
    const cookieArr = document.cookie.split(";");
    for (let i = 0; i < cookieArr.length; i++) {
      const cookiePair = cookieArr[i].split("=");
      if (name === cookiePair[0].trim()) {
        return decodeURIComponent(cookiePair[1]);
      }
    }
    return null;
  }

  setCookie(name: string, value: string, days: number) {
    const date = new Date();
    date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
    const expires = "expires=" + date.toUTCString();
    document.cookie = name + "=" + value + ";" + expires + ";path=/";
  }

  wrongPerson() {
    document.cookie = "username=; expires=Thu, 02 Feb 2025 00:00:01 GMT; path=/";
    location.reload();
  }
}


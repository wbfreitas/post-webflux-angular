import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MessageModel } from './message-model';

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  url = 'http://localhost:8080/chat';
  constructor(private http: HttpClient) { }

  getMessageStream(chatId: number): Observable<MessageModel> {
    return new Observable(observer => {
      const eventSource = new EventSource(`${this.url}/${chatId}`);

      eventSource.onmessage = (event) => {
        console.log(event);
        observer.next(JSON.parse(event.data));
      };

      eventSource.onerror = (error) => {
        observer.error(error);
        eventSource.close();
      };

      return () => {
        eventSource.close();
      };
    });
  }

  sendMessage(msg: MessageModel) {
      return this.http.post(this.url, msg);
  }
}

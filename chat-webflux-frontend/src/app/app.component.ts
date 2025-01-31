import { CommonModule } from '@angular/common';
import { Component, inject, NgZone, OnInit } from '@angular/core';
import { ActivatedRoute, RouterModule  } from '@angular/router';
import { ChatService } from './chat.service';
import { MessageModel } from './message-model';
import { FormsModule } from '@angular/forms'; 

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  styleUrl: './app.component.scss',

})
export class AppComponent implements OnInit {

  title = 'chat-webflux-front';
  private route = inject(ActivatedRoute);
  private service = inject(ChatService);
  private ngZone = inject(NgZone); 

  userName: string = '';
  chatId: number = 0;
  messages: MessageModel[] = [];
  newMessage: string = '';

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => { // Use queryParams
      this.userName = params['userName'] || null;
      this.chatId = parseInt(params['chatId'] || '0');
      if(this.chatId != 0)
       this.listennerMessage(this.chatId);
    });
  }

  sendMessage() {
    if (this.newMessage.trim() !== '') {
      this.service.sendMessage({
        userName: this.userName,
        chatId: this.chatId,
        text: this.newMessage
      }).subscribe(() => this.newMessage = '');
    }
  }

  private listennerMessage(chatId: number): void {
    this.service.getMessageStream(chatId).subscribe(msg => {
      console.log(msg);
      this.ngZone.run(() => { // Execute dentro da zona do Angular
        this.messages.push(msg);
        console.log("aqui");
      });
    });
  } 

}

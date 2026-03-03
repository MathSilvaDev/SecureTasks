import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { TaskResponse } from './model/task-response';
import { HomeService } from './home.service';

@Component({
  selector: 'app-home',
  imports: [FormsModule],
  templateUrl: './home.html',
  styleUrl: './home.scss',
})
export class Home {

  listItems: TaskResponse[] = [];
  title: string = '';
  description: string = '';

  constructor(private homeService: HomeService) { }

  ngOnInit(){
    this.loadTasks();
  }
  
  loadTasks(){
    this.homeService.getAllTasks().subscribe({
      next: (task) => {
        this.listItems = task;
      },
      error: (err) => {
        console.error('Error fetching tasks:', err);
      }
    });
  }

  createTask(){
    const title = this.title.trim();
    const description = this.description.trim();

    this.homeService.createTask(title, description).subscribe({
      next: (task) => {
        this.listItems.push(task);
      },
      error: (err) => {
        console.log('Error for add task', err);
      }
    });
  }

  deleteTask(id: number){
    this.homeService.deleteTask(id).subscribe({
      next: () => {
        this.listItems = this.listItems.filter((item) => item.id !== id)
      },
      error: (err) => {

      }
    })
  }
}

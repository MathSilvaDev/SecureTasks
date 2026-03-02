import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TaskResponse } from './model/task-response';

@Injectable({
  providedIn: 'root',
})
export class HomeService {
  
  private readonly API_URL = '/api/tasks'

  constructor(private http: HttpClient) { }

  getAllTasks(): Observable<TaskResponse[]> {
    return this.http.get<TaskResponse[]>(`${this.API_URL}/all`);
  }

  createTask(title: string, description: string): Observable<TaskResponse> {
    return this.http.post<TaskResponse>(`${this.API_URL}/create`, {
      title,
      description
    })
  }

  completeTask(id: number): Observable<void> {
    return this.http.patch<void>(`${this.API_URL}/${id}/completed`, { id });
  }

  deleteTask(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API_URL}/${id}/delete`);
  }
}


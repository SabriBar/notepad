import { Component } from '@angular/core';
import { NoteService } from '../../services/note.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-note',
  templateUrl: './create-note.component.html',
  styleUrls: ['./create-note.component.css']
})
export class CreateNoteComponent {

  createNoteForm!: FormGroup;

  constructor(
    private noteService: NoteService,
    private fb: FormBuilder,
    private router: Router){}

    ngOnInit(){
      this.createNoteForm = this.fb.group({
        title:['', [Validators.required]],
        content:['', Validators.required],
        tags:[''],
        archived:[false]

      })
    }
    createNote(): void {
      // Verifica si el formulario es válido antes de la creación
      if (this.createNoteForm.invalid) {
        // Manejo de error o mensaje al usuario
        console.log('Por favor, complete los campos requeridos correctamente.');
        return;
      }
  
      // Extrae los valores del formulario y envía la solicitud de creación
      const noteData = this.createNoteForm.value;
      this.noteService.createNote(noteData).subscribe(() => {
        // Redirigir a la lista después de la creación
        this.router.navigate(['/']);
      });
    
  }
}

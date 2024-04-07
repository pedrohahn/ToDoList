package br.edu.satc.todolistbase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.room.Room
import br.edu.satc.todolistbase.data.ToDoItemAdapter
import br.edu.satc.todolistbase.roomdatabase.AppDatabase
import br.edu.satc.todolistbase.roomdatabase.ToDoItem

class NewEditToDoItemActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var btSave: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_edit_to_do_item)

        initDatabase()

        etTitle = findViewById(R.id.et_title)
        etDescription = findViewById(R.id.et_description)
        btSave = findViewById(R.id.bt_save)

        btSave.setOnClickListener{
            save()
        }
    }
    // Criar o objeto do Todoitem
    // Salvar no banco// mostrar msg que salvou
    // fechar a tela
    private fun save(){
        //cria
        val toDoItem = ToDoItem(
            null,
            etTitle.text.toString(),
            etDescription.text.toString(),
            false
        )

        // salva
        db.toDoItemDao().insertAll(toDoItem)

        // mensagem
        Toast.makeText(this, "Tarefa salva com sucesso", Toast.LENGTH_SHORT).show()

        //fechar tela
        finish()
    }

    private lateinit var db: AppDatabase
    private fun initDatabase() {
        // Inicializar nosso banco de dados Android Room Persistence com SQLITE
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-todolist"
        )
            .allowMainThreadQueries()
            .build()

        // Utilizar a interface DAO para interagir com o database
//        db.toDoItemDao().getAll()
//        db.toDoItemDao().getToDoItem(itemId)
//        db.toDoItemDao().insertAll(toDoItem)

    }
}
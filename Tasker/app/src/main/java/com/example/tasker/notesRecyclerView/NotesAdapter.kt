package com.example.tasker.notesRecyclerView

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.tasker.data.Repository
import com.example.tasker.data.room.Task
import com.example.tasker.databinding.ItemTaskBinding

class NotesAdapter(
    private val repository: Repository,
    private val categoryName: String,
) : RecyclerView.Adapter<NotesViewHolder>() {

    private var data: List<Task> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Task>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NotesViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        with(holder.binding) {
            title.text = data[position].title
            date.text = data[position].deadline
            if (categoryName == "completed") {
                complete.isVisible = false
            } else {
                complete.setOnClickListener {
                    val task = Task(
                        id = data[position].id,
                        title = data[position].title,
                        projectName = data[position].projectName,
                        deadline = data[position].deadline,
                        isCompleted = true,
                    )
                    repository.addTask(task)
                    complete.setBackgroundColor(Color.GREEN)
//                data = repository.getAllTasksFromCategory(categoryName)
                }
            }
        }
    }
}

class NotesViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

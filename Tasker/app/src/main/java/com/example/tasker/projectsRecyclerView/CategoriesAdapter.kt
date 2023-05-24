package com.example.tasker.projectsRecyclerView

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasker.data.Repository
import com.example.tasker.data.room.Category
import com.example.tasker.databinding.ItemCategoryBinding

class CategoriesAdapter(
    private val repository: Repository,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<ProjectsViewHolder>() {
    private var data: List<Category> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Category>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectsViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectsViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ProjectsViewHolder, position: Int) {
        with(holder.binding) {
            title.text = data[position].title
            root.setOnClickListener {
                onClick(data[position].title)
            }
        }
    }
}

class ProjectsViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

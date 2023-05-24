package com.example.tasker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tasker.data.Repository
import com.example.tasker.data.room.Task
import com.example.tasker.databinding.FragmentAddNoteBinding

class AddNoteFragment : Fragment() {

    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: Repository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        repository = (context?.applicationContext as App).repository
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.getString("categoryName") != null) {
            categoryName = (arguments?.getString("categoryName")).toString()
        }

        binding.add.setOnClickListener {
            val task = Task(
                id = null,
                title = binding.taskTitle.text.toString(),
                projectName = categoryName,
                deadline = binding.taskDeadline.text.toString(),
                isCompleted = false,
            )
            repository.addTask(task)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private var categoryName = ""
    }
}

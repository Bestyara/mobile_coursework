package com.example.tasker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tasker.data.Repository
import com.example.tasker.databinding.FragmentNotesBinding
import com.example.tasker.notesRecyclerView.NotesAdapter

class NotesFragment : Fragment() {

    private var _binding: FragmentNotesBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: Repository
    private lateinit var adapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        repository = (context?.applicationContext as App).repository
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUI()
        if (categoryName == "completed") {
            adapter.setData(repository.getAllCompletedTasks())
            binding.textView2.text = "Выполненные задачи"
            binding.addNote.isVisible = false;
        } else {
            adapter.setData(repository.getNotCompletedTasksFromCategory(categoryName))
            binding.addNote.isVisible = true;
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUI() {
        if (arguments?.getString("categoryName") != null) {
            categoryName = (arguments?.getString("categoryName")).toString()
        }

        adapter = NotesAdapter(repository, categoryName)
        binding.notesList.adapter = adapter

        binding.addNote.setOnClickListener {
            val bundle = bundleOf("categoryName" to categoryName)
            findNavController().navigate(R.id.navigate_notesScreen_to_addNoteScreen, bundle)
        }
    }

    companion object {
        private var categoryName = ""
    }
}

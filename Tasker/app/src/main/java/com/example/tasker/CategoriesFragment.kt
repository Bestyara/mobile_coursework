package com.example.tasker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tasker.data.Repository
import com.example.tasker.databinding.FragmentProjectsBinding
import com.example.tasker.projectsRecyclerView.CategoriesAdapter

class CategoriesFragment : Fragment() {

    private var _binding: FragmentProjectsBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: Repository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProjectsBinding.inflate(inflater, container, false)
        repository = (context?.applicationContext as App).repository
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoriesAdapter(repository) { categoryName ->
            onItemClick(categoryName)
        }

        binding.projectsList.adapter = adapter
        adapter.setData(repository.getAllCategories())

        binding.addCategory.setOnClickListener {
            findNavController().navigate(R.id.navigate_categoryScreen_to_addCategoryName)
        }

        binding.openCompletedTasks.setOnClickListener {
            val bundle = bundleOf("categoryName" to "completed")
            findNavController().navigate(R.id.navigate_categoriesScreen_to_notesScreen, bundle)
        }
    }

    private fun onItemClick(categoryName: String) {
        val bundle = bundleOf("categoryName" to categoryName)
        findNavController().navigate(R.id.navigate_categoriesScreen_to_notesScreen, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

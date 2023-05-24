package com.example.tasker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tasker.databinding.FragmentStartBinding
import kotlin.system.exitProcess

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.start.setOnClickListener {
            findNavController().navigate(R.id.navigate_startScreen_to_projectsScreen)
        }
        binding.exit.setOnClickListener {
            exitProcess(1);
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.example.androidbottomnavigationviewtest.ui.fragments.tasks

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidbottomnavigationviewtest.databinding.FragmentTasksBinding
import com.example.androidbottomnavigationviewtest.ui.model.Task
import com.example.androidbottomnavigationviewtest.ui.service.RecyclerAdapter

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RecyclerAdapter
    private lateinit var tasks: ArrayList<Task>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tasks = arrayListOf()
        tasks.add(Task("первое", true))
        tasks.add(Task("второе", false))
        tasks.add(Task("третье", true))
        tasks.add(Task("четвертое", false))
        tasks.add(Task("пятое", true))

        adapter = RecyclerAdapter(tasks)
        binding.recyclerRV.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerRV.adapter = adapter

        binding.addBTN.setOnClickListener {
            if (binding.taskET.text.isNotEmpty()){
                tasks.add(Task(binding.taskET.text.toString(), false))
                adapter.notifyDataSetChanged()
                binding.taskET.text.clear()
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
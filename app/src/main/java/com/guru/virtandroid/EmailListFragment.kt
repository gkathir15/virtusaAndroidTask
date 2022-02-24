package com.guru.virtandroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class EmailListFragment : Fragment() {

    val viewModel:SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_email_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progress = view.findViewById<ProgressBar>(R.id.progress_circular)
        val recyclerV = view.findViewById<RecyclerView>(R.id.recycler)
        viewModel.also {
            it.fetchList()
            it.emailList.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    progress.visibility = View.VISIBLE
                } else {
                    recyclerV.visibility = View.VISIBLE
                    progress.visibility = View.GONE
                    recyclerV.also { it1->
                        it1.layoutManager = LinearLayoutManager(requireContext())
                        it1.adapter = StringAdapter(it)
                    }
                }
            }
        }
    }
}
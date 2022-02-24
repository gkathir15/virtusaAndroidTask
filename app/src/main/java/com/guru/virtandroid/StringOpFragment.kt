package com.guru.virtandroid

import android.os.Bundle
import android.service.autofill.TextValueSanitizer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.google.android.material.button.MaterialButton
import org.w3c.dom.Text


class StringOpFragment : Fragment() {


    val viewModel:SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_string_op, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<EditText>(R.id.ip1).addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                viewModel.input1.value = p0.toString()
            }
        })
        view.findViewById<EditText>(R.id.ip2).addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
               viewModel.input2.value = p0.toString()
            }
        })

        view.findViewById<MaterialButton>(R.id.clear_text).setOnClickListener {
            view.findViewById<EditText>(R.id.ip2).setText("")
            view.findViewById<EditText>(R.id.ip1).setText("")
        }

        val resultMerger = MediatorLiveData<String>().also {
            it.addSource(viewModel.input1) { it1 ->
                if(it1.isNotBlank())
                {
                    it.value = viewModel.input2.value?.let { it2 -> it1.findDifference(it2) }
                }
            }
            it.addSource(viewModel.input2) { it2 ->
                if(it2.isNotBlank())
                {
                    it.value = viewModel.input1.value?.findDifference(it2)
                }
            }
        }
        val resultMerger2 = MediatorLiveData<String>().also {
            it.addSource(viewModel.input1) { it1 ->
                if(it1.isNotBlank())
                {
                    it.value = viewModel.input2.value?.findDifference(it1)
                }
            }
            it.addSource(viewModel.input2) { it2 ->
                if(it2.isNotBlank())
                {
                    it.value = viewModel.input1.value?.let { it1 -> it2.findDifference(it1) }
                }
            }
        }


        //Mediator Live data to update views
        resultMerger.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.result).text = "Op1:   $it"
        }

          resultMerger2.observe(viewLifecycleOwner) {
            view.findViewById<TextView>(R.id.result2).text = "Op2:  $it"
        }


    }


    //Extension function to compute diff
    private fun String.findDifference(input2:String):String
    {
        val array= (this.toCharArray().toSet().minus(input2.toCharArray().toSet())).toCharArray()
        print(array)
        println(String(array))
        return if(array.isNotEmpty())
            String(array)
        else "<null>"

    }
}

package com.example.globallogic.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.globallogic.R
import com.example.globallogic.databinding.FragmentItemListBinding
import com.example.globallogic.domain.ItemResponse
import com.example.globallogic.presentation.list.adapter.ListAdapter
import com.example.globallogic.presentation.list.viewmodel.ListViewModel
import com.example.globallogic.utils.Status
import kotlinx.android.synthetic.main.fragment_item_list.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A fragment representing a list of Items.
 */
class ListFragment : Fragment() {

    private val viewModel by viewModel<ListViewModel>()
    private lateinit var adapter: ListAdapter
    private lateinit var binding: FragmentItemListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_item_list, container, false
        )
        val view: View = binding.root

        initViews()

        subscribeViewModel(view)

        return view
    }

    private fun subscribeViewModel(view: View) {
        viewModel.getItems().observe(viewLifecycleOwner, Observer {
            it?.let {resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        view.list.visibility = View.VISIBLE
                        view.progressBar.visibility = View.GONE
                        resource.data?.let { response -> retrieveList(response) }
                    }
                    Status.ERROR -> {
                        view.list.visibility = View.VISIBLE
                        view.progressBar.visibility = View.GONE
                        Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        view.progressBar.visibility = View.VISIBLE
                        view.list.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun initViews() {
        binding.list.layoutManager = LinearLayoutManager(requireActivity())
        adapter = ListAdapter(binding.list, ListAdapter.ThumbnailListener {
            val action = ListFragmentDirections.actionItemFragmentToDetailFragment(it)
            findNavController().navigate(action)
        })
        binding.list.adapter = adapter
    }

    private fun retrieveList(list: List<ItemResponse>) {
        adapter.apply {
            setData(list)
        }
    }
}
package com.example.globallogic.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.globallogic.R
import com.example.globallogic.databinding.FragmentDetailBinding
import com.example.globallogic.domain.ItemResponse
import com.example.globallogic.presentation.detail.viewmodel.DetailViewModel
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBinding
    private lateinit var viewmodel : DetailViewModel
    private lateinit var item : ItemResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detail, container, false
        )
        val view: View = binding.root

        item = args.Item

        initViewModel()

        setValues()

        initViews()

        return view
    }

    private fun initViews() {
        Picasso.get()
            .load(item.image)
            .resize(100, 100)
            .centerCrop()
            .error(R.drawable.ic_baseline_not_interested_24)
            .placeholder(R.drawable.ic_baseline_not_interested_24)
            .into(binding.imageView)
        binding.viewmodel = viewmodel
    }

    private fun setValues() {
        viewmodel.setItem(item)
    }

    private fun initViewModel() {
        viewmodel = ViewModelProvider(this).get(DetailViewModel::class.java)
    }
}
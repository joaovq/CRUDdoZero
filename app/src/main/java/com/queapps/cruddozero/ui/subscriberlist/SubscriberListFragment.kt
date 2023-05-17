package com.queapps.cruddozero.ui.subscriberlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Layout.Directions
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.queapps.cruddozero.R
import com.queapps.cruddozero.data.db.AppDatabase
import com.queapps.cruddozero.data.db.entity.SubscriberEntity
import com.queapps.cruddozero.databinding.FragmentSubscriberListBinding
import com.queapps.cruddozero.extension.navigateWithAnimations
import com.queapps.cruddozero.repository.DatabaseDataSource
import com.queapps.cruddozero.repository.SubscriberRepository

class SubscriberListFragment : Fragment() {

    private val viewModel : SubscriberListViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val databaseDataSource:SubscriberRepository =
                    DatabaseDataSource(AppDatabase.getInstance(requireContext()).subscriberDao)

                return SubscriberListViewModel(databaseDataSource) as T
            }
        }
    }

    private val _binding by lazy {
        FragmentSubscriberListBinding.inflate(layoutInflater)
    }

    val binding :FragmentSubscriberListBinding
        get() = _binding

    override fun onCreateView(
        inflater : LayoutInflater, container: ViewGroup?,
        savedInstanceState : Bundle?
    ) : View {
        return _binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModelEvents()
        configureViewListeners()

    }

    private fun observeViewModelEvents() {
        viewModel.allSubscribersEvent.observe(viewLifecycleOwner){ allSubscribers ->
            val subscriberListAdapter : SubscriberListAdapter= SubscriberListAdapter(
                allSubscribers
            ).apply {
                onItemClick = { subscriber->
                    val directions = SubscriberListFragmentDirections
                        .actionSubscriberListFragmentToSubscriberFragment(subscriber)

                    findNavController().navigateWithAnimations(
                        directions = directions
                    )
                }
            }

            _binding.recyclerSubscribes.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(requireContext())
                adapter = subscriberListAdapter
            }
        }

    }

    override fun onStart() {
        super.onStart()
        viewModel.getSubscribers()
    }

    private fun configureViewListeners(){
        _binding.fabAddSubscriber.setOnClickListener {
//            O certo é chamar pelo id da navegação
//            findNavController().navigate(R.id.action_subscriberListFragment_to_subscriberFragment)
            findNavController()
                .navigateWithAnimations(R.id.action_subscriberListFragment_to_subscriberFragment)
        }
    }
}
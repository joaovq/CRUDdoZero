package com.queapps.cruddozero.ui.subscriber

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import com.queapps.cruddozero.R
import com.queapps.cruddozero.data.db.AppDatabase
import com.queapps.cruddozero.data.db.dao.SubscriberDao
import com.queapps.cruddozero.databinding.FragmentSubscriberBinding
import com.queapps.cruddozero.extension.hideKeyboard
import com.queapps.cruddozero.repository.DatabaseDataSource
import com.queapps.cruddozero.repository.SubscriberRepository

class SubscriberFragment : Fragment() {

    private val viewModel: SubscriberViewModel by viewModels{
        object :ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {

                val subscriberDao :SubscriberDao = AppDatabase
                    .getInstance(requireContext())
                    .subscriberDao

                val repository:SubscriberRepository = DatabaseDataSource(subscriberDao)

                return SubscriberViewModel(repository) as T
            }
        }
    }

    private val args:SubscriberFragmentArgs by navArgs()

    private val _binding by lazy {
        FragmentSubscriberBinding.inflate(LayoutInflater.from(requireContext()))
    }

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        binding = FragmentSubscriberBinding.inflate(layoutInflater, container, false)

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args.subscriber?.let {
            subscriber ->
            _binding.buttonSubscriber.text = getString(R.string.subscriber_button_update)
            _binding.inputEmail.setText(subscriber.email)
            _binding.inputName.setText(subscriber.name)

            _binding.buttonDelete.visibility = View.VISIBLE
        }

        observeEvents()
        setListeners()
    }

    private fun observeEvents() {
        viewModel.subscriberStateEventData.observe(viewLifecycleOwner){
            subscriberState ->
            when(subscriberState){
                is SubscriberViewModel.SubscriberState.Inserted,
                is SubscriberViewModel.SubscriberState.Updated,
                SubscriberViewModel.SubscriberState.Deleted -> {
                    clearFields()
                    hideKeyboard()
                    requireView().requestFocus()
                    findNavController().popBackStack()
                }
            }
            viewModel.messageEventData.observe(viewLifecycleOwner){
                stringResId -> Snackbar.make(
                requireView(),
                stringResId,
                Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun clearFields() {
        _binding.inputName.text?.clear()
        _binding.inputEmail.text?.clear()
    }

//    Fazer uma extesion para utilizar em varios lugares da aplicação
    private fun hideKeyboard(){
        val parentActivity = requireActivity()
        if (parentActivity is AppCompatActivity){
            parentActivity.hideKeyboard()
        }
    }

    private fun setListeners() {
        _binding.buttonSubscriber.setOnClickListener {
            val name = _binding.inputName.text.toString()
            val email = _binding.inputEmail.text.toString()

            viewModel.addOrUpdateSubscriber(name, email, args.subscriber?.id ?: 0)
        }

        _binding.buttonDelete.setOnClickListener {
            viewModel.removeSubscriber(args.subscriber?.id ?: 0)
        }
    }
}


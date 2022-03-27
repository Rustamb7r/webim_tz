package ru.webim.tz.presentation.tickets

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_HIDDEN
import ru.webim.tz.databinding.FragmentTicketListBinding
import ru.webim.tz.di.list.TicketListFragmentScope
import ru.webim.tz.domain.model.AscendingOrder
import ru.webim.tz.domain.model.DescendingOrder
import ru.webim.tz.presentation.MainActivity
import ru.webim.tz.presentation.base.BaseApplication
import ru.webim.tz.presentation.base.BaseFragment
import ru.webim.tz.presentation.tickets.adapter.TicketAdapter
import javax.inject.Inject

@TicketListFragmentScope
class TicketListFragment : BaseFragment<FragmentTicketListBinding>() {

    @Inject
    lateinit var viewModel: TicketListViewModel

    private var adapter = TicketAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as? BaseApplication)
            ?.appComponent
            ?.listComponent()
            ?.create()
            ?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBindings()
        setupBottomSheet()
        subscribeObservers()
    }

    private fun setupBindings() = binding?.apply {
        ticketList.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.loadTickets()
        }
        logoutImageView.setOnClickListener {
            viewModel.logout()
        }
        setupFloatingButton()
        setupFilterSelection()
    }

    private fun setupFilterSelection() {
        binding?.ascendingButton?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                viewModel.selectFilter(AscendingOrder())
            }
        }

        binding?.descendingButton?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                viewModel.selectFilter(DescendingOrder())
            }
        }
    }

    private fun setupFloatingButton() {
        val llBottomSheet = binding?.bottomSheet ?: return
        binding?.filterButton?.setOnClickListener {
            if (BottomSheetBehavior.from(llBottomSheet).state != STATE_EXPANDED) {
                val displayMetrics = DisplayMetrics()
                activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
                BottomSheetBehavior.from(llBottomSheet).setState(STATE_EXPANDED)
            } else {
                BottomSheetBehavior.from(llBottomSheet).setState(STATE_HIDDEN)
            }
        }
    }

    private fun setupBottomSheet() {
        val llBottomSheet = binding?.bottomSheet ?: return
        val displayMetrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        BottomSheetBehavior.from(llBottomSheet).peekHeight =
            (displayMetrics.heightPixels * 0.7).toInt()
        val params = llBottomSheet.layoutParams as ViewGroup.LayoutParams
        params.height = (displayMetrics.heightPixels * 0.7).toInt()

        BottomSheetBehavior.from(llBottomSheet).state = STATE_HIDDEN
    }

    private fun subscribeObservers() {
        viewModel.isAuthenticated.observe(viewLifecycleOwner) {
            if (it.not()) {
                (activity as? MainActivity)?.openAuthFragment()
            }
        }

        viewModel.ticketList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding?.noTicketsView?.isVisible =
                it.isNullOrEmpty() && viewModel.isLoading.value?.not() == true
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding?.swipeRefreshLayout?.isRefreshing = it
        }

        viewModel.sortType.observe(viewLifecycleOwner) {
            if (it is AscendingOrder) {
                binding?.ascendingButton?.isChecked = true
            } else {
                binding?.descendingButton?.isChecked = true
            }
        }
    }
}
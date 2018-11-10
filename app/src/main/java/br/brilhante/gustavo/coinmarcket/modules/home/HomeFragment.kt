package br.brilhante.gustavo.coinmarcket.modules.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import br.brilhante.gustavo.coinmarcket.R
import br.brilhante.gustavo.coinmarcket.extensions.verticalLinearLayout
import br.brilhante.gustavo.coinmarcket.models.Coin
import br.brilhante.gustavo.coinmarcket.modules.home.adapter.CoinListener
import br.brilhante.gustavo.coinmarcket.modules.home.adapter.HomeAdapter
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(), CoinListener {

    private var viewModel: HomeViewModel? = null

    private var adapter = HomeAdapter(this)

    private var recyclerView: RecyclerView? = null

    override fun onSaveInstanceState(outState: Bundle) {
        recyclerView?.layoutManager?.let {
            outState.putParcelable(
                "LayoutManagerInstance",
                it.onSaveInstanceState()
            )
        }
        super.onSaveInstanceState(outState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.recyclerView
        view.recyclerView.adapter = adapter
        context?.let {
            view.recyclerView.verticalLinearLayout(it)
        }
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
        registerObservables()
        savedInstanceState?.let { savedInstance: Bundle ->
            recyclerView?.layoutManager?.onRestoreInstanceState(savedInstance.getParcelable("LayoutManagerInstance"))
        }
    }

    private fun registerObservables() {
        registerGetCoinsList()
    }

    private fun registerGetCoinsList() {
        viewModel?.coinLiveData?.observe(this, Observer { list: List<Coin> ->
            if (list.isNotEmpty()) {
                adapter.coinList = list
            } else {
                context?.let {
                    viewModel?.startList(it)
                }
            }
        })
    }

    override fun onCoinClick(coin: Coin) {
        coin.id?.let {
            val directions = HomeFragmentDirections.action_homeFragment_to_detailFragment()
            directions.setCoinId(it)
            NavHostFragment.findNavController(this).navigate(directions)
        }
    }
}

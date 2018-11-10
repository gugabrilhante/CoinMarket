package br.brilhante.gustavo.coinmarcket.modules.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.brilhante.gustavo.coinmarcket.R
import kotlinx.android.synthetic.main.fragment_detail.view.*

class DetailFragment : Fragment() {

    lateinit var nameTextView: TextView
    lateinit var marketCapTextView: TextView
    lateinit var priceTextView: TextView

    private var viewModel: DetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(DetailViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        nameTextView = view.nameTextView
        marketCapTextView = view.marketCapTextView
        priceTextView = view.priceTextView
        viewModel?.setupCoinId(DetailFragmentArgs.fromBundle(arguments).coinId)
        registerObservables()
        return view
    }

    private fun registerObservables() {
        registerCoinObservable()
    }

    private fun registerCoinObservable() {
        viewModel?.coinLiveData?.observe(this, Observer {
            context?.let { context: Context ->
                nameTextView.text = String.format(context.getString(R.string.name_coin), it.name, it.symbol)
                marketCapTextView.text = String.format(
                    context.getString(R.string.market_cap),
                    it.quotes?.uSD?.marketCap ?: kotlin.run { "not found" })
                priceTextView.text = String.format(
                    context.getString(R.string.price),
                    it.quotes?.uSD?.price ?: kotlin.run { "not found" })
            }
        })
    }

}

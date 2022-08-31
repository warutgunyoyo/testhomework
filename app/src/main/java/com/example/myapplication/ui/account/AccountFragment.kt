package com.example.myapplication.ui.account

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.example.myapplication.Utils.OnSnapPositionChangeListener
import com.example.myapplication.Utils.SnapOnScrollListener
import com.example.myapplication.adapter.BalanceAdapter
import com.example.myapplication.adapter.StateMentAdapter
import com.example.myapplication.databinding.FragmentAccountBinding
import com.example.myapplication.model.MoneyTop
import com.example.myapplication.model.StateMentModel


class AccountFragment : Fragment(),OnSnapPositionChangeListener  {

    private var _binding: FragmentAccountBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var balanceAdapter: BalanceAdapter
    private lateinit var textPage:TextView
    private lateinit var listData:List<MoneyTop>
    private lateinit var progresBar:ProgressBar
    private lateinit var progresBarStateMent:ProgressBar
    private lateinit var buttonImage:ImageButton
    private lateinit var transfer_menu:LinearLayout
    private lateinit var top_up_menu:LinearLayout
    private lateinit var bill_menu:LinearLayout
    private lateinit var more_menu:LinearLayout




    /////bottom rcview
    private lateinit var recyclerViewStatement: RecyclerView
    private lateinit var stateMentAdapter: StateMentAdapter


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val accountViewModel =
            ViewModelProvider.AndroidViewModelFactory(this.requireActivity().application).create(
                AccountViewModel::class.java)

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        ///binding Widget////////////////////////////////////////////////////////
        recyclerView=binding.rcViewTop
        recyclerViewStatement=binding.recycleViewTransaction
        textPage=binding.textPage
        progresBar=binding.progressCircular
        progresBarStateMent=binding.progressCircularStatement
        buttonImage=binding.btnTop
        buttonImage.setOnClickListener {
            showToast(buttonImage)
        }
        transfer_menu=binding.transferMenu

        transfer_menu.setOnClickListener {
            showToast(transfer_menu)
        }
        top_up_menu=binding.topUpMenu
        top_up_menu.setOnClickListener {
            showToast(top_up_menu)
        }
        bill_menu=binding.billMenu
        bill_menu.setOnClickListener {
            showToast(bill_menu)
        }
        more_menu=binding.moreMenu
        more_menu.setOnClickListener {
            showToast(more_menu)
        }
        ////////////Call APi in case mocking Data Delay 1Sec ////////////////////
        accountViewModel.loadMoneyTop()
        accountViewModel.loadStatement()

        //////////////observe Data change/////////////////////////////////
        accountViewModel.moneyTops.observe(viewLifecycleOwner) {
//            textView.text = it[0].toString()
            listData=it
                setTopRecycle(it)
        }
        accountViewModel.statementMoney.observe(viewLifecycleOwner){
            setStateMentRecycleView(it)
        }
        return root
    }

    private fun setStateMentRecycleView(list: List<StateMentModel>?) {
        progresBarStateMent.visibility=View.GONE
        recyclerViewStatement.visibility=View.VISIBLE
        stateMentAdapter=StateMentAdapter(list!!)

        recyclerViewStatement.layoutManager=LinearLayoutManager(this.requireContext(),RecyclerView.VERTICAL,false)
        recyclerViewStatement.addItemDecoration(
            DividerItemDecoration(
                recyclerViewStatement.context,
                DividerItemDecoration.VERTICAL
            )
        )


        recyclerViewStatement.adapter=stateMentAdapter
    }

    private fun setTopRecycle(list: List<MoneyTop>) {
        progresBar.visibility=View.GONE
        recyclerView.visibility=View.VISIBLE
        recyclerView.setHasFixedSize(true)
        var onSnapPositionChangeListener:OnSnapPositionChangeListener
        recyclerView.layoutManager=LinearLayoutManager(this.requireContext(),RecyclerView.HORIZONTAL,false)
        balanceAdapter= BalanceAdapter(list)
        val snapHelper:SnapHelper=LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
        val snapOnScrollListener = SnapOnScrollListener(snapHelper,
            SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL, this)
        recyclerView.addOnScrollListener(snapOnScrollListener)

        recyclerView.adapter=balanceAdapter

    }
    fun SnapHelper.getSnapPosition(recyclerView: RecyclerView): Int {
        val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
        val snapView = findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        return layoutManager.getPosition(snapView)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun showToast(view:View){
        val toast =Toast.makeText(this.requireContext(),"Clicked",Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM,0,160)
        toast.show()
    }

    override fun onSnapPositionChange(position: Int) {
        var nowPage=position+1
        textPage.text="$nowPage"+"/"+"${listData.size}"
    }
}
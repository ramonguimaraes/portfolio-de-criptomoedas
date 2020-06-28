package com.ramongapolinario.portfoliodecriptomoedas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ramongapolinario.portfoliodecriptomoedas.R
import com.ramongapolinario.portfoliodecriptomoedas.adapters.RegisterAdapter
import com.ramongapolinario.portfoliodecriptomoedas.db.RegisterDAO
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ctx = activity!!.applicationContext
        val registerDao = RegisterDAO(ctx)

        val lst = registerDao.getAll()
        val adapter = RegisterAdapter(lst)

        rv_history.adapter = adapter
        rv_history.layoutManager = LinearLayoutManager(ctx)
    }

}
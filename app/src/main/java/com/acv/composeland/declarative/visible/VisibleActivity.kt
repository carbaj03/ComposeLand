package com.acv.composeland.declarative.visible

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.acv.composeland.R
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.lifecycleScope

import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class VisibleActivity : AppCompatActivity() {

    lateinit var adapter: CustomAdapter
    lateinit var recyclerView: RecyclerView


    override fun onCreate(rememberSaveable: Bundle?) {
        super.onCreate(rememberSaveable)
        setContentView(R.layout.activity_view)
        val progress = findViewById<ContentLoadingProgressBar>(R.id.progress)
        lifecycleScope.launch {
            progress.visibility = View.VISIBLE
            recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
                visibility = View.GONE
                layoutManager = LinearLayoutManager(this@VisibleActivity)
                adapter = CustomAdapter(service.fetchData())
                visibility = View.VISIBLE
            }
            progress.visibility = View.GONE
        }

    }
}

class CustomAdapter(private val dataSet: Array<String>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.text_row_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = dataSet[position]
    }

    override fun getItemCount() = dataSet.size
}

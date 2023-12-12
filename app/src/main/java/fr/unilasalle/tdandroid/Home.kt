package fr.unilasalle.tdandroid

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val spanCount = calculateSpanCount()
        val layoutManager = GridLayoutManager(this, spanCount)
        recyclerView.layoutManager = layoutManager
    }

    private fun calculateSpanCount(): Int {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        val columnWidthDp = 180
        return Math.max(1, (screenWidthDp / columnWidthDp).toInt())
    }
}
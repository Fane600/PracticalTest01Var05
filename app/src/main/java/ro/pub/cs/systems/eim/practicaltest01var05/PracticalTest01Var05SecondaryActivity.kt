package ro.pub.cs.systems.eim.practicaltest01var05

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PracticalTest01Var05SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var05_secondary)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val counterTextView = findViewById<TextView>(R.id.counter_text_view)
        val verifyButton = findViewById<Button>(R.id.verify_button)
        val cancelButton = findViewById<Button>(R.id.cancel_button)

        val counter = intent.getIntExtra("counter", 0)
        counterTextView.text = counter.toString()

        verifyButton.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

        cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}

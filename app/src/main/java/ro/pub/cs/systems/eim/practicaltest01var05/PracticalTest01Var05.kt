package ro.pub.cs.systems.eim.practicaltest01var05

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button // B.1: ca să poți folosi tipul Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {

    // Contorul tuturor apăsărilor pe butoane (câmp al clasei, ca să fie văzut
    // și din onSaveInstanceState / onRestoreInstanceState)
    private var counter: Int = 0

    private lateinit var centerText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_practical_test01_var05_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        centerText = findViewById(R.id.textView)
        val leftTop = findViewById<Button>(R.id.button_top_left)
        val rightTop = findViewById<Button>(R.id.button_top_right)
        val leftBottom = findViewById<Button>(R.id.button_bottom_left)
        val rightBottom = findViewById<Button>(R.id.button_bottom_right)
        val centerButton = findViewById<Button>(R.id.button_center)

        leftTop.setOnClickListener {
            onButtonPressed("Top Left")
        }
        rightTop.setOnClickListener {
            onButtonPressed("Top Right")
        }
        leftBottom.setOnClickListener {
            onButtonPressed("Bottom Left")
        }
        rightBottom.setOnClickListener {
            onButtonPressed("Bottom Right")
        }
        centerButton.setOnClickListener {
            onButtonPressed("Center")
        }

        val secondaryButton = findViewById<Button>(R.id.button_secondary_activity)
        secondaryButton.setOnClickListener {
            val intent = Intent(this, PracticalTest01Var05SecondaryActivity::class.java)
            intent.putExtra("counter", counter)
            secondaryLauncher.launch(intent)
        }

        logMessage(intent)
    }

    // Dacă activitatea e deja deschisă, intent-ul de la adb vine aici
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        logMessage(intent)
    }

    private fun logMessage(intent: Intent) {
        val message = intent.getStringExtra("message")
        if (message != null) {
            Log.d("PracticalTest01Var05", "Mesaj primit: $message")
        }
    }

    private val secondaryLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val message = if (result.resultCode == RESULT_OK) "Verify" else "Cancel"
            Toast.makeText(this, "Ai apasat: $message", Toast.LENGTH_SHORT).show()
        }

    private fun onButtonPressed(name: String) {
        appendName(centerText, name)
        counter++
        Toast.makeText(this, "Counter: $counter", Toast.LENGTH_SHORT).show()
    }
    private fun appendName(textView: TextView, name: String) {
        if (textView.text.isNotEmpty()) {
            textView.append(", ")
        }
        textView.append(name)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("counter", counter)
        outState.putString("centerText", centerText.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        counter = savedInstanceState.getInt("counter", 0)
        centerText.text = savedInstanceState.getString("centerText", "")
        Toast.makeText(this, "Counter restaurat: $counter", Toast.LENGTH_SHORT).show()
    }
}

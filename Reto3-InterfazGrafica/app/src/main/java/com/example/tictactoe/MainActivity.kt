package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener  {

    private val even: Array<Button> = Array (1, 0);
    private val odd: Array<Button> = intArrayOf(1, 1, 1);
    private val buttons: Array<Array<Button>> = arrayOf(even, odd)

    private var player1Turn = true

    private var roundCount: Int = 0

    private var player1Points: Int = 0
    private var player2Points: Int = 0

    private var textViewPlayer1: TextView? = null
    private var textViewPlayer2: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewPlayer1 = findViewById(R.id.text_view_p1)
        textViewPlayer2 = findViewById(R.id.text_view_p2)

        for (i in 0..2) {
            for (j in 0..2) {
                val buttonID = "button_$i$j"
                val resID = resources.getIdentifier(buttonID, "id", packageName)
                buttons[i][j] = findViewById<View>(resID) as Button
                buttons[i][j].setOnClickListener(this)
            }

        }
    }

    override fun onClick(p0: View?) {
        if ((p0 as Button).text.toString() != "") {
            return
        }

        if (player1Turn) {
            (p0 as Button).text = "X"
        } else {
            (p0 as Button).text = "O"
        }

        roundCount++

        if (checkForWin()) {
            if (player1Turn) {
                player1Wins()
            } else {
                player2Wins()
            }
        } else if (roundCount === 9) {
            draw()
        } else {
            player1Turn = !player1Turn
        }
    }

    private fun checkForWin(): Boolean {
        val field = Array<Array<String>>(3) {  }

        for (i in 0..2) {
            for (j in 0..2) {
                field[i][j] = buttons[i][j].text.toString()
            }
        }

        for (i in 0..2) {
            if (field[i][0] == field[i][1]
                && field[i][0] == field[i][2]
                && field[i][0] != ""
            ) {
                return true
            }
        }

        for (i in 0..2) {
            if (field[0][i] == field[1][i]
                && field[0][i] == field[2][i]
                && field[0][i] != ""
            ) {
                return true
            }
        }

        if (field[0][0] == field[1][1]
            && field[0][0] == field[2][2]
            && field[0][0] != ""
        ) {
            return true
        }

        if (field[0][2] == field[1][1] && field[0][2] == field[2][0] && field[0][2] != "") {
            return true
        }else{
            return false
        }

    }

    private fun player1Wins() {
        player1Points++
        Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show()

    }

    private fun player2Wins() {
        player2Points++
        Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show()

    }

    private fun draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show()

    }

}

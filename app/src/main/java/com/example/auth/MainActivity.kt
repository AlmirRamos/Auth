package com.example.auth

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.auth.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configCliks()
    }

    private fun configCliks() {

        binding.btnLogin.setOnClickListener {
            binding.textTitulo.text = "Entre com sua conta"
            binding.layoutLogin.visibility = View.VISIBLE
            binding.layoutCadastro.visibility = View.GONE
            binding.layoutRecuperarSenha.visibility = View.GONE
            binding.btnLogin.background = resources.getDrawable(R.drawable.bg_swith_left, null)
            binding.btnCadastro.background = null
            binding.btnLogin.setTextColor(resources.getColor(R.color.white, null))
            binding.btnLogin.setTypeface(null, Typeface.BOLD)
            binding.btnCadastro.setTypeface(null, Typeface.NORMAL)
            binding.btnLogin.textSize = 20F
            binding.btnCadastro.textSize = 14F

        }

        binding.btnCadastro.setOnClickListener {

            binding.textTitulo.text = "Crie sua conta"
            binding.layoutLogin.visibility = View.GONE
            binding.layoutCadastro.visibility = View.VISIBLE
            binding.layoutRecuperarSenha.visibility = View.GONE
            binding.btnCadastro.background = resources.getDrawable(R.drawable.bg_swith_right, null)
            binding.btnLogin.background = null
            binding.btnCadastro.setTextColor(resources.getColor(R.color.white, null))
            binding.btnLogin.setTypeface(null, Typeface.NORMAL)
            binding.btnCadastro.setTypeface(null, Typeface.BOLD)
            binding.btnCadastro.textSize = 20F
            binding.btnLogin.textSize = 14F
        }

        binding.lRebootSenha.setOnClickListener {

            binding.textTitulo.text = "Recupere sua senha"
            binding.layoutLogin.visibility = View.GONE
            binding.layoutRecuperarSenha.visibility = View.VISIBLE
        }

        binding.btnEntrar.setOnClickListener {

            binding.progressBar.visibility = View.VISIBLE

            val telaLogin = binding.layoutLogin.visibility
            val telaCadatro = binding.layoutCadastro.visibility

            if (telaLogin == View.VISIBLE){
                validarDadosLogin()
            }else if(telaCadatro == View.VISIBLE){
                validarDadosCadastro()
            }else{
                validarRecoverSenhar()
            }

        }
    }

    private fun validarDadosLogin() {

        val email = binding.loginEmail.text.toString().trim()
        val password = binding.loginSenha.text.toString()

        if (!email.isEmpty()){
            if (!password.isEmpty()){

                Toast.makeText(this, "Bem vindo!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                binding.progressBar.visibility = View.GONE

            }else{
                binding.loginSenha.requestFocus()
                binding.loginSenha.error = "Adicione sua senha"
            }
        }else{
            binding.loginEmail.requestFocus()
            binding.loginEmail.error = "Adicione email"
        }

        binding.progressBar.visibility = View.GONE
    }

    private fun validarDadosCadastro(){

        val nome = binding.cadastroNome.text.toString().trim()
        val email = binding.cadastroEmail.text.toString().trim()
        val password = binding.cadastroSenha.text.toString().trim()
        val confirmPassword = binding.cadastroConfirmarSenha.text.toString().trim()

        if (!nome.isEmpty()){
            if (!email.isEmpty()){
                if (!password.isEmpty()){
                    if (!confirmPassword.isEmpty()){

                        Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        binding.progressBar.visibility = View.GONE

                    }else{
                        binding.cadastroConfirmarSenha.requestFocus()
                        binding.cadastroConfirmarSenha.error = "Confirme sua senha"
                    }

                }else{
                    binding.cadastroSenha.requestFocus()
                    binding.cadastroSenha.error = "Adicione uma senha"
                }

            }else {
                binding.cadastroEmail.requestFocus()
                binding.cadastroEmail.error = "Adicione seu email"
            }
        }else {
            binding.cadastroNome.requestFocus()
            binding.cadastroNome.error = "Adicione seu nome"
        }

        binding.progressBar.visibility = View.GONE

    }

    private fun validarRecoverSenhar(){

        val email = binding.recuperarEmail.text.toString()
        if (!email.isEmpty()) {

            Toast.makeText(this, "Verifique seu email para atualizar a senha!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            binding.progressBar.visibility = View.GONE

        }else{
            binding.recuperarEmail.requestFocus()
            binding.recuperarEmail.error = "Adicione o email para recuperar a senha"
        }
    }
}
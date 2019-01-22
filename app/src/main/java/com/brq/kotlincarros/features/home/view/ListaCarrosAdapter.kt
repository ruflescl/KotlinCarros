package com.brq.kotlincarros.features.home.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.brq.kotlincarros.R
import com.brq.kotlincarros.features.home.vo.CarroVO
import kotlinx.android.synthetic.main.list_item_carro.view.*

class ListaCarrosAdapter(var listaCarros: List<CarroVO>?, val context: Context)
    // As heranças no Kotlin são representadas desta forma no código (:)
    : RecyclerView.Adapter<ListaCarrosAdapter.ViewHolder>() {

    fun configurarLista(listaCarros: List<CarroVO>) {
        this.listaCarros = listaCarros
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context)
                .inflate(R.layout.list_item_carro, parent, false)

        return ViewHolder(view)
    }

    // Funções 'one-liner' podem simplificar a leitura do código removendo as chaves '{}'
    override fun getItemCount(): Int = listaCarros?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        // 'let' é uma função que recebe como argumento o objeto que a está invocando
        // (neste caso é o CarroVO), permitindo o acesso a ele com o valor-parâmetro 'it'
        listaCarros?.get(position).let {

            // Como o holder é 'nullable' (ViewHolder'?'), devemos fazer chamadas 'safe'
            // encadeadas (.?)
            holder?.imagemId?.setBackgroundResource(it?.imagemId ?: 0)

            // Ou podemos 'arriscar' um NPE e fazer as chamadas através de
            // 'non-null assertion' (!!.)
            holder!!.preco.text = context.getString(R.string.preco, it?.preco ?: 0.0)

            // Como foi feita uma 'non-null assertion' anteriormente, as próximas não
            // são necessárias (ver warning no '!!')
            holder!!.ano.text = context.getString(R.string.ano, it?.ano ?: 0)
            holder!!.modelo.text = context.getString(R.string.modelo, it?.modelo ?: "")
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagemId: ImageView
        val ano: TextView
        val modelo: TextView
        val preco: TextView

        // a função 'init' roda junto com o construtor padrão (ViewHolder'(...)') e
        // faz o papel de inicialização da classe
        init {
            imagemId = itemView.img_carro
            ano = itemView.tv_ano
            modelo = itemView.tv_modelo
            preco = itemView.tv_preco
        }
    }
}


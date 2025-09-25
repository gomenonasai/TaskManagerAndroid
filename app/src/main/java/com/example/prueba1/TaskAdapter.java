package com.example.prueba1;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<Task> taskList; // lista de tareas a mostrar

    public TaskAdapter(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflar o crear en memoria el diseño de cada tarjeta de tarea
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        //obtener tarea actual

        Task task = taskList.get(position);

        // asignar valores a cada TextView
        holder.tvTitle.setText(task.getTitulo());
        holder.tvDescription.setText(task.getDescripcion());
        holder.tvCategory.setText("Categoría: " + task.getCategoria());
        holder.tvPriority.setText("Prioridad: " + task.getPrioridad());
        holder.tvUrgency.setText("Urgencia: " + task.getUrgencia() + " ★");

        // Mostrar/ocultar "IMPORTANTE"
        if (task.isImportante()) {
            holder.tvImportant.setVisibility(View.VISIBLE);
            holder.tvImportant.setText("¡IMPORTANTE!");
        } else {
            holder.tvImportant.setVisibility(View.GONE);
        }

        // para marcar como completada
        holder.chkCompletada.setOnCheckedChangeListener(null); // evitar trigger al reciclar
        holder.chkCompletada.setChecked(task.isCompletada());

        // si está completada tachar título
        if (task.isCompletada()) {
            holder.tvTitle.setPaintFlags(holder.tvTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.tvTitle.setPaintFlags(holder.tvTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        }

        // evento al marcar o desmarcar el checkbox
        holder.chkCompletada.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setCompletada(isChecked);
            if (isChecked) {
                holder.tvTitle.setPaintFlags(holder.tvTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                holder.tvTitle.setPaintFlags(holder.tvTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList != null ? taskList.size() : 0;
    }

    // clase que representa los elementos de cada tarjeta (ViewHolder)
    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvCategory, tvPriority, tvUrgency, tvImportant;
        CheckBox chkCompletada;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.txtTitulo);
            tvDescription = itemView.findViewById(R.id.txtDescripcion);
            tvCategory = itemView.findViewById(R.id.txtCategoria);
            tvPriority = itemView.findViewById(R.id.txtPrioridad);
            tvUrgency = itemView.findViewById(R.id.txtUrgencia);
            tvImportant = itemView.findViewById(R.id.txtImportante);
            chkCompletada = itemView.findViewById(R.id.chkCompletada);
        }
    }
}

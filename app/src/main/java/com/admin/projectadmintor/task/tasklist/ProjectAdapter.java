package com.admin.projectadmintor.task.tasklist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.admin.projectadmintor.R;
import com.admin.projectadmintor.mvp.view.Imp.ScheduleActivity;
import com.admin.projectadmintor.mvp.view.SignActivity;
import com.admin.projectadmintor.routeActivity;

import java.util.List;

/**
 * Created by admin on 2018/1/2.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
    private List<Project> mProjectList;
    private String stuid;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mProjectName;
        TextView mProjectId;
        TextView mProjectContent;
        View projectView;
        public ViewHolder(View itemView) {
            super(itemView);
            mProjectId=(TextView)itemView.findViewById(R.id.my_id);
            mProjectName=(TextView)itemView.findViewById(R.id.my_name);
            mProjectContent=(TextView)itemView.findViewById(R.id.my_content);
            projectView=itemView;
        }
    }
    public ProjectAdapter(List<Project> projectList, String stuid) {
        mProjectList = projectList;
        this.stuid=stuid;
    }
    @Override
    public ProjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ltem_project, null);
        final ViewHolder holder = new ViewHolder(view);
        final ImageButton schedule=(ImageButton)view.findViewById(R.id.schedule);
        final ImageButton sign=(ImageButton)view.findViewById(R.id.sign);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent=new Intent(v.getContext(), SignActivity.class);
                Project project=mProjectList.get(position);
                String mpid=project.getId();
                intent.putExtra("projectid",mpid);
                intent.putExtra("studentid",stuid);
                v.getContext().startActivity(intent);
            }
        });
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent=new Intent(v.getContext(), ScheduleActivity.class);
                Project project=mProjectList.get(position);
                String mpid=project.getId();
                intent.putExtra("projectid",mpid);
                intent.putExtra("studentid",stuid);
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ProjectAdapter.ViewHolder holder, int position) {
        Project project = mProjectList.get(position);
        holder.mProjectName.setText(project.getId());
        holder.mProjectId.setText(project.getName());
        holder.mProjectContent.setText(project.getContent());
    }

    @Override
    public int getItemCount() {
        return mProjectList.size();
    }
}

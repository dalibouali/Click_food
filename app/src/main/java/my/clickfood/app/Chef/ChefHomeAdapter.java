package my.clickfood.app.Chef;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import my.clickfood.app.R;


//this class define the adapter
public class ChefHomeAdapter extends RecyclerView.Adapter<ChefHomeAdapter.ViewHolder> {
    private Context mcont;
    private List<UpdateDishModel> updateDishModellist;

    public ChefHomeAdapter(Context context,List<UpdateDishModel>updateDishModellist)
    {
        this.updateDishModellist=updateDishModellist;
        this.mcont=context;
    }

    @NonNull
    @Override
    public ChefHomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //convert xml file into an object of type view (chef_menu_update_delete contains the layout of each item on the recycler view list)
        View view= LayoutInflater.from(mcont).inflate(R.layout.chef_menu_update_delete,parent,false);
        return new ChefHomeAdapter.ViewHolder(view);
    }
    //method called by recyclerview to display the data at the specific position
    @Override
    public void onBindViewHolder(@NonNull ChefHomeAdapter.ViewHolder holder, int position) {
        final UpdateDishModel updateDishModel=updateDishModellist.get(position);
        holder.dishes.setText(updateDishModel.getDishes());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            //onclicked item of recyclerView go and update the dish
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mcont,Update_Delete_Dish.class);
                intent.putExtra("updatedeletedish",updateDishModel.getRandomUID());
                mcont.startActivity(intent);

            }
        });
    }
    //return number of items in the collection
    @Override
    public int getItemCount() {
        return updateDishModellist.size();
    }

    //Permet de représenter un élément de la liste de données dans le RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dishes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dishes=itemView.findViewById(R.id.dish_name);

        }
    }
}

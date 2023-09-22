package me.gutierri.shoppinglist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ProductItem[] localDataSet;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView priceTextView;
        private final TextView quantityTextView;

        public ViewHolder(View view) {
            super(view);
            nameTextView = (TextView) view.findViewById(R.id.nameProduct);
            priceTextView = (TextView) view.findViewById(R.id.priceProduct);
            quantityTextView = (TextView) view.findViewById(R.id.quantityProduct);
        }

        public TextView getNameTextView() {
            return nameTextView;
        }

        public TextView getPriceTextView() {
            return priceTextView;
        }

        public TextView getQuantityTextView() {
            return quantityTextView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter(ProductItem[] dataSet) {
        localDataSet = dataSet;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_product, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        ProductItem product = localDataSet[position];

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getNameTextView().setText(product.name);
        viewHolder.getPriceTextView().setText(product.price);
        viewHolder.getQuantityTextView().setText(product.quantity);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.length;
    }
}

class ProductItem {
    protected  String name = "Pão de forma, 320g";
    protected String price = "R$ 15,80 / R$ R$ 7,99";
    protected String quantity = "x2";
}

public class MainActivity extends AppCompatActivity {
    FloatingActionButton addProductButton;
    Button monthHeaderSelectionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addProductButton = (FloatingActionButton) findViewById(R.id.fab);
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Adicionar Produto");
                builder.setView(getLayoutInflater().inflate(R.layout.dialog_add_product, null));
                builder.setPositiveButton("Adicionar", (dialog, which) -> {

                });
                builder.setNegativeButton("Cancelar", (dialog, which) -> {

                });
                builder.create().show();
            }
        });

        monthHeaderSelectionButton = (Button) findViewById(R.id.monthHeaderSelectionButton);
        monthHeaderSelectionButton.setOnClickListener(new View.OnClickListener() {
            int checkedItem;
            AlertDialog s;
            String[] monthsName = new String[]{"Janeiro", "Fevereiro", "Maarço", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro"};
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Lista do mês");
                builder.setSingleChoiceItems(monthsName, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("<HeaderButton>", "Clicked on Button Month");
                        s.dismiss();
                    }
                });
                s = builder.create();
                s.show();
            }
        });

        ProductItem[] indexProductsItem = new ProductItem[]{
            new ProductItem(),
            new ProductItem(),
            new ProductItem(),
            new ProductItem(),
            new ProductItem(),
            new ProductItem(),
            new ProductItem(),
            new ProductItem(),
            new ProductItem(),
            new ProductItem()
        };
        RecyclerView recyclerView = findViewById(R.id.recycler);
        CustomAdapter indexProducts = new CustomAdapter(indexProductsItem); /*new String[10]*/
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(
            getApplicationContext()
        );

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(indexProducts);
    }
}
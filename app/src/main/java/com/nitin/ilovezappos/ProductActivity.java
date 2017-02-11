package com.nitin.ilovezappos;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.nitin.ilovezappos.databinding.ProductActivityBinding;
import com.nitin.ilovezappos.pojo.Product;
import com.nitin.ilovezappos.pojo.ProductResponse;
import com.nitin.ilovezappos.retrofit.ApiClient;
import com.nitin.ilovezappos.retrofit.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductActivity extends AppCompatActivity {
    private static final String TAG = ProductActivity.class.getSimpleName();
    private final static String API_KEY = "b743e26728e16b81da139182bb2094357c31d331";
    ProductActivityBinding binding;
    String productUrl;
    FloatingActionButton fab;
    String productId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.product_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (savedInstanceState != null) {
            productId = savedInstanceState.getString("productId");
        }
        setSupportActionBar(toolbar);
        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY first", Toast.LENGTH_LONG).show();
            return;
        }
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Added to Cart", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                fab.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward));
                fab.setImageResource(R.drawable.check);
            }
        });
        Button button = (Button) findViewById(R.id.cart);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.setImageResource(R.drawable.plus);
                fab.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward));
                fab.setImageResource(R.drawable.check);
                Snackbar.make(v, "Added to Cart", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Uri data = getIntent().getData();
        if (data != null) {
            List<String> params = data.getPathSegments();
            productId = params.get(1);
        }

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ProductResponse> call;
        if (productId != null) {
            call = apiService.getProducts(productId, API_KEY);
        } else {
            call = apiService.getProducts("", API_KEY);
        }
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                List<Product> products = response.body().getResults();
                if (products.size() > 0) {
                    binding.setProduct(products.get(0));
                    productUrl = products.get(0).getProductUrl();
                    productId = products.get(0).getProductId();
                    fab.setImageResource(R.drawable.plus);
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
                createDialog(t);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product, menu);
        return true;
    }

    private void shareTextUrl() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_TEXT, productUrl);
        startActivity(Intent.createChooser(share, "Share Product"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            shareTextUrl();
            return true;
        }
        if (id == R.id.search) {
            SearchView search = (SearchView) item.getActionView();

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    ApiInterface apiService =
                            ApiClient.getClient().create(ApiInterface.class);

                    Call<ProductResponse> call = apiService.getProducts(query, API_KEY);
                    call.enqueue(new Callback<ProductResponse>() {
                        @Override
                        public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                            List<Product> products = response.body().getResults();
                            if (products.size() > 0) {
                                binding.setProduct(products.get(0));
                                productUrl = products.get(0).getProductUrl();
                                productId = products.get(0).getProductId();
                                fab.setImageResource(R.drawable.plus);
                            }
                        }


                        @Override
                        public void onFailure(Call<ProductResponse> call, Throwable t) {
                            Log.e(TAG, t.toString());
                            createDialog(t);
                        }
                    });
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    ApiInterface apiService =
                            ApiClient.getClient().create(ApiInterface.class);

                    Call<ProductResponse> call = apiService.getProducts(newText, API_KEY);
                    call.enqueue(new Callback<ProductResponse>() {
                        @Override
                        public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                            List<Product> products = response.body().getResults();
                            if (products.size() > 0) {
                                binding.setProduct(products.get(0));
                                productUrl = products.get(0).getProductUrl();
                                productId = products.get(0).getProductId();
                                fab.setImageResource(R.drawable.plus);
                            }
                        }

                        @Override
                        public void onFailure(Call<ProductResponse> call, Throwable t) {
                            Log.e(TAG, t.toString());
                            createDialog(t);
                        }
                    });
                    return true;
                }
            });
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("productId", productId);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        productId = savedInstanceState.getString("productId");
    }

    void createDialog(Throwable throwable) {
        new AlertDialog.Builder(ProductActivity.this)
                .setTitle("ERROR")
                .setMessage("Please check your internet connection")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(R.mipmap.ic_launcher)
                .show();
    }
}

package xyz.codem.randomdinner;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ItemFragment extends Fragment {
    private RecyclerView recyclerView;
    private DinnerAdapter mAdapter;
    DinnerArray dinners;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.entry_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit:
                // TODO: do something
                return true;
            case R.id.delete:
                DinnerList list = DinnerList.get(getContext());
                list.removeArray(dinners);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("0","启动");
        Log.d("size",dinners.getAllEntry().size()+"");


        View view = inflater.inflate(R.layout.activity_main, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();

        return view;
    }

    private void updateUI() {
        List<String> dinner = dinners.getAllEntry();
        Log.d("list",dinner.size()+"");

        mAdapter = new DinnerAdapter(dinner);
        recyclerView.setAdapter(mAdapter);
    }


    private class DinnerHolder extends RecyclerView.ViewHolder{
        private TextView mTitle;

        public DinnerHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.activity_single_choice, parent, false));
            mTitle = itemView.findViewById(R.id.item_name);
        }

        public void bind(String dinner) {
            mTitle.setText(dinner);

        }
    }

    private class DinnerAdapter extends RecyclerView.Adapter<DinnerHolder> {

        private List<String> mDinners;

        public DinnerAdapter(List<String> dinners) {
            mDinners = dinners;
            Log.d("size",dinners.size()+"");
        }

        @Override
        public DinnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new DinnerHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(DinnerHolder holder, int position) {
            holder.bind(mDinners.get(position));

        }

        @Override
        public int getItemCount() {
            Log.d("size",mDinners.size()+"");

            return mDinners.size();
        }
    }
}

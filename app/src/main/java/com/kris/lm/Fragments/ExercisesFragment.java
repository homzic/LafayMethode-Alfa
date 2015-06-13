package com.kris.lm.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kris.lm.R;
import com.kris.lm.Recycler_Cwiczenia.CwiczenieItem;
import com.kris.lm.Recycler_Cwiczenia.SwipeableRecyclerViewTouchListener;

import java.util.ArrayList;
import java.util.List;

public class ExercisesFragment extends Fragment {
    private CardViewAdapter mAdapter;
    private List<CwiczenieItem> cItems;

    public ExercisesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_exercises, container, false);
    }

    @Override
    public void onStart() {


    ListaCwiczen();
        int L = cItems.size();
        Log.e("mItems ", "Lista cwiczen " + L);
        ArrayList<String> mItems = new ArrayList<>(L);
        for (int i = 0; i < L; i++) {
            mItems.add(String.format("%02d", i));
        }

        OnItemTouchListener itemTouchListener = new OnItemTouchListener() {

            @Override
            public void onCardViewTap(View view, int position) {

                //         Toast.makeText(Cwiczenia.this, "Tapped " + mItems.get(position), Toast.LENGTH_SHORT).show();
            }
        };

        mAdapter = new CardViewAdapter(mItems, itemTouchListener);

        RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
        recyclerView.setAdapter(mAdapter);

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(recyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipe(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    //Toast.makeText(Cwiczenia.this, mItems.get(position) + " swiped left", Toast.LENGTH_SHORT).show();
                                    //        mItems.remove(position);
                                    //        mAdapter.notifyItemRemoved(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
//                                    Toast.makeText(Cwiczenia.this, mItems.get(position) + " swiped right", Toast.LENGTH_SHORT).show();
                                    //       mItems.remove(position);
                                    //       mAdapter.notifyItemRemoved(position);
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                        });

        recyclerView.addOnItemTouchListener(swipeTouchListener);
        super.onStart();

    }
    /**
     * Interface for the touch events in each item
     */
    public interface OnItemTouchListener {
        /**
         * Callback invoked when the user Taps one of the RecyclerView items
         *
         * @param view     the CardView touched
         * @param position the index of the item touched in the RecyclerView
         */
        void onCardViewTap(View view, int position);

        /**
         * Callback invoked when the Button1 of an item is touched
         *
         * @param view     the Button touched
         * @param position the index of the item touched in the RecyclerView
         */

    }

    /**
     * A simple adapter that loads a CardView layout with one TextView and two Buttons, and
     * listens to clicks on the Buttons or on the CardView
     */
    public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
        private final List<String> cards;
        private final OnItemTouchListener onItemTouchListener;

        public CardViewAdapter(List<String> cards, OnItemTouchListener onItemTouchListener) {
            this.cards = cards;
            this.onItemTouchListener = onItemTouchListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cwiczenia_view_item, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            CwiczenieItem cwiczenieItem = cItems.get(i);

            viewHolder.title.setText(cwiczenieItem.getName());
            viewHolder.tvDesNature.setText(cwiczenieItem.getDes());
            viewHolder.imgThumbnail.setImageResource(cwiczenieItem.getThumbnail());
            viewHolder.imgDifficulty.setImageResource(cwiczenieItem.getmDifficulty());

        }

        @Override
        public int getItemCount() {
            return cards == null ? 0 : cards.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView title;
            public final ImageView imgThumbnail;
            public final ImageView imgDifficulty;
            public final TextView tvDesNature;
            private final TextView readOpis;


            public ViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.card_view_title);
                imgThumbnail = (ImageView) itemView.findViewById(R.id.cwIcon);
                tvDesNature = (TextView) itemView.findViewById(R.id.cwDesc);
                imgDifficulty = (ImageView) itemView.findViewById(R.id.trudnoscIcon);
                readOpis = (TextView) itemView.findViewById(R.id.textReadOpis);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemTouchListener.onCardViewTap(v, getPosition());
                        if (tvDesNature.getVisibility() == View.VISIBLE) {
                            tvDesNature.setVisibility(View.GONE);
                            readOpis.setText(getString(R.string.pokaz_opis));
                        } else {
                            tvDesNature.setVisibility(View.VISIBLE);
                            readOpis.setText(getString(R.string.zwin_opis));
                        }
                    }
                });
            }
        }
    }

    private void ListaCwiczen() {
        cItems = new ArrayList<>();
        CwiczenieItem cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " A");
        cwiczenie.setDes(getString(R.string.cw_A_opis));
        cwiczenie.setThumbnail(R.drawable.cw_a);
        cwiczenie.setmDifficulty(R.drawable.skill1);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " A1");
        cwiczenie.setDes(getString(R.string.cw_A1_opis));
        cwiczenie.setThumbnail(R.drawable.cw_a1);
        cwiczenie.setmDifficulty(R.drawable.skill1);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " B");
        cwiczenie.setDes(getString(R.string.cw_B_opis));
        cwiczenie.setThumbnail(R.drawable.cw_b);
        cwiczenie.setmDifficulty(R.drawable.skill2);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " C");
        cwiczenie.setDes(getString(R.string.cw_C_opis));
        cwiczenie.setThumbnail(R.drawable.cw_c);
        cwiczenie.setmDifficulty(R.drawable.skill2);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " C1");
        cwiczenie.setDes(getString(R.string.cw_C1_opis));
        cwiczenie.setThumbnail(R.drawable.cw_c1);
        cwiczenie.setmDifficulty(R.drawable.skill1);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " D");
        cwiczenie.setDes(getString(R.string.cw_D_opis));
        cwiczenie.setThumbnail(R.drawable.cw_d);
        cwiczenie.setmDifficulty(R.drawable.skill1);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " E");
        cwiczenie.setDes(getString(R.string.cw_E_opis));
        cwiczenie.setThumbnail(R.drawable.cw_e);
        cwiczenie.setmDifficulty(R.drawable.skill1);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " F");
        cwiczenie.setDes(getString(R.string.cw_F_opis));
        cwiczenie.setThumbnail(R.drawable.cw_f);
        cwiczenie.setmDifficulty(R.drawable.skill1);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " G");
        cwiczenie.setDes(getString(R.string.cw_G_opis));
        cwiczenie.setThumbnail(R.drawable.cw_g);
        cwiczenie.setmDifficulty(R.drawable.skill1);
        cItems.add(cwiczenie);

        cwiczenie = new CwiczenieItem();
        cwiczenie.setName(getString(R.string.cwiczenie) + " K2");
        cwiczenie.setDes(getString(R.string.cw_K2_opis));
        cwiczenie.setThumbnail(R.drawable.cw_k2);
        cwiczenie.setmDifficulty(R.drawable.skill1);
        cItems.add(cwiczenie);

    }

}

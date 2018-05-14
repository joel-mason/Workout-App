package com.company.joeliomason.projectme.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.company.joeliomason.projectme.ColorWheel;
import com.company.joeliomason.projectme.Database.CardDatabaseAdapter2;
import com.company.joeliomason.projectme.POJOs.Card;
import com.company.joeliomason.projectme.POJOs.Set;
import com.company.joeliomason.projectme.R;
import com.company.joeliomason.projectme.Views.EditCardioActivity;
import com.company.joeliomason.projectme.Views.EditExerciseActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by joelmason on 01/04/2015.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.GeneralViewHolder> {

    private List<Card> cards;
    private Context mContext;
    private ColorWheel mColorWheel = new ColorWheel();
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private DatabaseReference mDatabase;
    private String userId;
    private String noSlashDate;


    public CardAdapter(Context context, List<Card> cards) {
        mContext = context;
        this.cards = cards;
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        userId = mFirebaseUser.getUid();
    }

    private String getNoSlashDate(String date) {
        String ret = "";
        for(char curr : date.toCharArray()) {
            if(curr != '/') {
                ret+=curr;
            }
        }
        return ret;
    }


    @Override
    public int getItemCount() {
        return cards.size();
    }



    @Override
    public void onBindViewHolder(GeneralViewHolder viewHolder1, int i) {
        Card card = cards.get(i);
        List<Set> sets = card.getSet();
        noSlashDate = getNoSlashDate(card.getDate());
        mDatabase = FirebaseDatabase.getInstance().getReference("users/" + userId + "/" + noSlashDate + "/" + card.getId());
        int count = 0;
        switch(viewHolder1.getItemViewType()) {
            case 1:
                ViewHolder1 vh1 = (ViewHolder1) viewHolder1;
                vh1.card = card;
                vh1.id2 = card.getId();
                vh1.vName.setText(cards.get(i).getName());

                for (Set set : sets) {
                    if (count == 0) {
                        vh1.category = set.getCategory();
                        Log.v("category", vh1.category + "");
                        vh1.rl.setBackgroundColor(mColorWheel.getColor(vh1.category));
                        vh1.vReps.setText(set.getReps() + "");
                        vh1.vWeight.setText(set.getWeight() + "");
                    }
                    count++;
                }
                break;

            case 2:
                ViewHolder2 vh2 = (ViewHolder2) viewHolder1;
                vh2.card = card;
                vh2.id2 = card.getId();
                vh2.vName.setText(cards.get(i).getName());

                for (Set set : sets) {
                    if (count == 0) {
                        vh2.category = set.getCategory();
                        vh2.rl.setBackgroundColor(mColorWheel.getColor(vh2.category));
                        vh2.vReps.setText(set.getReps() + "");
                        vh2.vWeight.setText(set.getWeight() + "");
                    }
                    if(count == 1) {
                        vh2.vReps1.setText(set.getReps() + "");
                        vh2.vWeight1.setText(set.getWeight() + "");
                    }
                    count++;
                }
                break;

            case 3:
                ViewHolder3 vh3 = (ViewHolder3) viewHolder1;
                vh3.card = card;
                vh3.id2 = card.getId();
                vh3.vName.setText(cards.get(i).getName());

                for (Set set : sets) {
                    if (count == 0) {
                        vh3.category = set.getCategory();
                        vh3.rl.setBackgroundColor(mColorWheel.getColor(vh3.category));
                        vh3.vReps.setText(set.getReps() + "");
                        vh3.vWeight.setText(set.getWeight() + "");
                    }
                    if(count == 1) {
                        vh3.vReps1.setText(set.getReps() + "");
                        vh3.vWeight1.setText(set.getWeight() + "");
                    }
                    if(count == 2) {
                        vh3.vReps2.setText(set.getReps() + "");
                        vh3.vWeight2.setText(set.getWeight() + "");
                    }
                    count++;
                }
                break;

            case 4:
                ViewHolder4 vh4 = (ViewHolder4) viewHolder1;
                vh4.card = card;
                vh4.id2 = card.getId();
                vh4.vName.setText(cards.get(i).getName());

                for (Set set : sets) {
                    if (count == 0) {
                        vh4.category = set.getCategory();
                        vh4.rl.setBackgroundColor(mColorWheel.getColor(vh4.category));
                        vh4.vReps.setText(set.getReps() + "");
                        vh4.vWeight.setText(set.getWeight() + "");
                    }
                    if(count == 1) {
                        vh4.vReps1.setText(set.getReps() + "");
                        vh4.vWeight1.setText(set.getWeight() + "");
                    }
                    if(count == 2) {
                        vh4.vReps2.setText(set.getReps() + "");
                        vh4.vWeight2.setText(set.getWeight() + "");
                    }
                    if(count == 3) {
                        vh4.vReps3.setText(set.getReps() + "");
                        vh4.vWeight3.setText(set.getWeight() + "");
                    }
                    count++;
                }
                break;
            case 5:
                ViewHolder5 vh5 = (ViewHolder5) viewHolder1;
                vh5.card = card;
                vh5.id2 = card.getId();
                vh5.vName.setText(cards.get(i).getName());

                for (Set set : sets) {
                    if (count == 0) {
                        vh5.category = set.getCategory();
                        vh5.rl.setBackgroundColor(mColorWheel.getColor(vh5.category));
                        vh5.vReps.setText(set.getReps() + "");
                        vh5.vWeight.setText(set.getWeight() + "");
                    }
                    if(count == 1) {
                        vh5.vReps1.setText(set.getReps() + "");
                        vh5.vWeight1.setText(set.getWeight() + "");
                    }
                    if(count == 2) {
                        vh5.vReps2.setText(set.getReps() + "");
                        vh5.vWeight2.setText(set.getWeight() + "");
                    }
                    if(count == 3) {
                        vh5.vReps3.setText(set.getReps() + "");
                        vh5.vWeight3.setText(set.getWeight() + "");
                    }
                    if(count == 4) {
                        vh5.vReps4.setText(set.getReps() + "");
                        vh5.vWeight4.setText(set.getWeight() + "");
                    }
                    if (count > 4) {
                        vh5.vShowMore.setText((count - 4) + " More");
                    }
                    count++;
                }
                break;

            case 6:
                ViewHolder6 vh6 = (ViewHolder6) viewHolder1;
                vh6.card = card;
                vh6.id2 = card.getId();
                vh6.vName.setText(cards.get(i).getName());

                for (Set set : sets) {
                    if (count == 0) {
                        vh6.category = set.getCategory();
                        Log.v("category", vh6.category + "");
                        vh6.rl.setBackgroundColor(mColorWheel.getColor(vh6.category));
                        vh6.vReps.setText(set.getReps() + "");
                        vh6.vWeight.setText(set.getWeight() + "");
                    }
                    count++;
                }
                break;

            case 7:
                ViewHolder7 vh7 = (ViewHolder7) viewHolder1;
                vh7.card = card;
                vh7.id2 = card.getId();
                vh7.vName.setText(cards.get(i).getName());

                for (Set set : sets) {
                    if (count == 0) {
                        vh7.category = set.getCategory();
                        vh7.rl.setBackgroundColor(mColorWheel.getColor(vh7.category));
                        vh7.vReps.setText(set.getReps() + "");
                        vh7.vWeight.setText(set.getWeight() + "");
                    }
                    if(count == 1) {
                        vh7.vReps1.setText(set.getReps() + "");
                        vh7.vWeight1.setText(set.getWeight() + "");
                    }
                    count++;
                }
                break;

            case 8:
                ViewHolder8 vh8 = (ViewHolder8) viewHolder1;
                vh8.card = card;
                vh8.id2 = card.getId();
                vh8.vName.setText(cards.get(i).getName());

                for (Set set : sets) {
                    if (count == 0) {
                        vh8.category = set.getCategory();
                        vh8.rl.setBackgroundColor(mColorWheel.getColor(vh8.category));
                        vh8.vReps.setText(set.getReps() + "");
                        vh8.vWeight.setText(set.getWeight() + "");
                    }
                    if(count == 1) {
                        vh8.vReps1.setText(set.getReps() + "");
                        vh8.vWeight1.setText(set.getWeight() + "");
                    }
                    if(count == 2) {
                        vh8.vReps2.setText(set.getReps() + "");
                        vh8.vWeight2.setText(set.getWeight() + "");
                    }
                    count++;
                }
                break;

            case 9:
                ViewHolder9 vh9 = (ViewHolder9) viewHolder1;
                vh9.card = card;
                vh9.id2 = card.getId();
                vh9.vName.setText(cards.get(i).getName());

                for (Set set : sets) {
                    if (count == 0) {
                        vh9.category = set.getCategory();
                        vh9.rl.setBackgroundColor(mColorWheel.getColor(vh9.category));
                        vh9.vReps.setText(set.getReps() + "");
                        vh9.vWeight.setText(set.getWeight() + "");
                    }
                    if(count == 1) {
                        vh9.vReps1.setText(set.getReps() + "");
                        vh9.vWeight1.setText(set.getWeight() + "");
                    }
                    if(count == 2) {
                        vh9.vReps2.setText(set.getReps() + "");
                        vh9.vWeight2.setText(set.getWeight() + "");
                    }
                    if(count == 3) {
                        vh9.vReps3.setText(set.getReps() + "");
                        vh9.vWeight3.setText(set.getWeight() + "");
                    }
                    count++;
                }
                break;
            case 10:
                ViewHolder10 vh10 = (ViewHolder10) viewHolder1;
                vh10.card = card;
                vh10.id2 = card.getId();
                vh10.vName.setText(cards.get(i).getName());

                for (Set set : sets) {
                    if (count == 0) {
                        vh10.category = set.getCategory();
                        vh10.rl.setBackgroundColor(mColorWheel.getColor(vh10.category));
                        vh10.vReps.setText(set.getReps() + "");
                        vh10.vWeight.setText(set.getWeight() + "");
                    }
                    if(count == 1) {
                        vh10.vReps1.setText(set.getReps() + "");
                        vh10.vWeight1.setText(set.getWeight() + "");
                    }
                    if(count == 2) {
                        vh10.vReps2.setText(set.getReps() + "");
                        vh10.vWeight2.setText(set.getWeight() + "");
                    }
                    if(count == 3) {
                        vh10.vReps3.setText(set.getReps() + "");
                        vh10.vWeight3.setText(set.getWeight() + "");
                    }
                    if(count == 4) {
                        vh10.vReps4.setText(set.getReps() + "");
                        vh10.vWeight4.setText(set.getWeight() + "");
                    }
                    if (count > 4) {
                        vh10.vShowMore.setText((count - 4) + " More");
                    }
                    count++;
                }
                break;
        }




    }

    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        int y;
        int returnValue = 1;
        Card c;
        c = cards.get(position);
        List<Set> x = c.getSet();
        if(!x.isEmpty()) {
            y = x.size();
            if(x.get(0).getCategory() != 7) {
                if (y < 2) {
                    returnValue = 1;
                } else if (y < 3) {
                    returnValue = 2;
                } else if (y < 4) {
                    returnValue = 3;
                } else if (y < 5) {
                    returnValue = 4;
                } else {
                    returnValue = 5;
                }
            } else if(x.get(0).getCategory() == 7) {
                if (y < 2) {
                    returnValue = 6;
                } else if (y < 3) {
                    returnValue = 7;
                } else if (y < 4) {
                    returnValue = 8;
                } else if (y < 5) {
                    returnValue = 9;
                } else {
                    returnValue = 10;
                }
            }
        }
        return returnValue;
    }

    @Override
    public GeneralViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {


        LayoutInflater mInflater = LayoutInflater.from ( viewGroup.getContext () );
        switch (viewType) {

            case 1:
                ViewGroup vg1 = (ViewGroup) mInflater.inflate(R.layout.card_view1, viewGroup, false);
                ViewHolder1 vh1 = new ViewHolder1(vg1);
                return vh1;

            case 2:
                ViewGroup vg2 = (ViewGroup) mInflater.inflate(R.layout.card_view2, viewGroup, false);
                ViewHolder2 vh2 = new ViewHolder2(vg2);
                return vh2;

            case 3:
                ViewGroup vg3 = (ViewGroup) mInflater.inflate(R.layout.card_view3, viewGroup, false);
                ViewHolder3 vh3 = new ViewHolder3(vg3);
                return vh3;

            case 4:
                ViewGroup vg4 = (ViewGroup) mInflater.inflate(R.layout.card_view4, viewGroup, false);
                ViewHolder4 vh4 = new ViewHolder4(vg4);
                return vh4;

            case 5:
                ViewGroup vg5 = (ViewGroup) mInflater.inflate(R.layout.card_view5, viewGroup, false);
                ViewHolder5 vh5 = new ViewHolder5(vg5);
                return vh5;

            case 6:
                ViewGroup vg6 = (ViewGroup) mInflater.inflate(R.layout.cardio_view1, viewGroup, false);
                ViewHolder6 vh6 = new ViewHolder6(vg6);
                return vh6;

            case 7:
                ViewGroup vg7 = (ViewGroup) mInflater.inflate(R.layout.cardio_view2, viewGroup, false);
                ViewHolder7 vh7 = new ViewHolder7(vg7);
                return vh7;

            case 8:
                ViewGroup vg8 = (ViewGroup) mInflater.inflate(R.layout.cardio_view3, viewGroup, false);
                ViewHolder8 vh8 = new ViewHolder8(vg8);
                return vh8;

            case 9:
                ViewGroup vg9 = (ViewGroup) mInflater.inflate(R.layout.cardio_view4, viewGroup, false);
                ViewHolder9 vh9 = new ViewHolder9(vg9);
                return vh9;

            case 10:
                ViewGroup vg10 = (ViewGroup) mInflater.inflate(R.layout.cardio_view5, viewGroup, false);
                ViewHolder10 vh10 = new ViewHolder10(vg10);
                return vh10;

            default:
                ViewGroup def = (ViewGroup) mInflater.inflate(R.layout.card_view1, viewGroup, false);
                ViewHolder1 def1 = new ViewHolder1(def);
                return def1;
        }



    }

    class GeneralViewHolder extends RecyclerView.ViewHolder {

        public GeneralViewHolder(View itemView) {
            super(itemView);
        }
    }


    class ViewHolder1 extends GeneralViewHolder {

        protected TextView vName;
        protected TextView vWeight;
        protected TextView vReps;
        protected TextView vShowMore;
        protected Card card;
        protected int id;
        protected String id2;
        protected int category;
        protected RelativeLayout rl;


        public ViewHolder1(View v) {
            super(v);

            rl = (RelativeLayout) v.findViewById(R.id.rl);

            vName = (TextView) v.findViewById(R.id.editText);
            vWeight = (TextView) v.findViewById(R.id.textView7);
            vReps = (TextView) v.findViewById(R.id.textView9);


            vShowMore = (TextView) v.findViewById(R.id.textView32);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("item clicked", vName.getText().toString());
                    Intent intent = new Intent(mContext.getApplicationContext(), EditExerciseActivity.class);
                    intent.putExtra("Card", card);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);

                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final CharSequence[] items = {"Yes", "No"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Delete " + vName.getText().toString());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            if (item == 0) {
                                mDatabase.removeValue();
                                cards.remove(item);
                                notifyDataSetChanged();
                                Toast.makeText(mContext, "Card Deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "Cancelled", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                    return true;
                }
            });


        }
    }

    class ViewHolder2 extends GeneralViewHolder {

        protected TextView vName;
        protected TextView vWeight;
        protected TextView vReps;
        protected TextView vWeight1;
        protected TextView vReps1;
        protected TextView vShowMore;
        protected Card card;
        protected int id;
        protected String id2;
        protected int category;
        protected RelativeLayout rl;


        public ViewHolder2(View v) {
            super(v);

            rl = (RelativeLayout) v.findViewById(R.id.rl);

            vName = (TextView) v.findViewById(R.id.editText);
            vWeight = (TextView) v.findViewById(R.id.textView7);
            vReps = (TextView) v.findViewById(R.id.textView9);

            vWeight1 = (TextView) v.findViewById(R.id.textView11);
            vReps1 = (TextView) v.findViewById(R.id.textView13);

            vShowMore = (TextView) v.findViewById(R.id.textView32);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("item clicked", vName.getText().toString());
                    Intent intent = new Intent(mContext.getApplicationContext(), EditExerciseActivity.class);
                    intent.putExtra("Card", card);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);

                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final CharSequence[] items = {"Yes", "No"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Delete " + vName.getText().toString());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            if (item == 0) {
                                mDatabase.removeValue();
                                cards.remove(item);
                                notifyDataSetChanged();
                                Toast.makeText(mContext, "Card Deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "Cancelled", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                    return true;
                }
            });


        }
    }

    class ViewHolder3 extends GeneralViewHolder {

        protected TextView vName;
        protected TextView vWeight;
        protected TextView vReps;
        protected TextView vWeight1;
        protected TextView vReps1;
        protected TextView vWeight2;
        protected TextView vReps2;
        protected TextView vShowMore;
        protected Card card;
        protected int id;
        protected String id2;
        protected int category;
        protected RelativeLayout rl;


        public ViewHolder3(View v) {
            super(v);

            rl = (RelativeLayout) v.findViewById(R.id.rl);

            vName = (TextView) v.findViewById(R.id.editText);
            vWeight = (TextView) v.findViewById(R.id.textView7);
            vReps = (TextView) v.findViewById(R.id.textView9);

            vWeight1 = (TextView) v.findViewById(R.id.textView11);
            vReps1 = (TextView) v.findViewById(R.id.textView13);

            vWeight2 = (TextView) v.findViewById(R.id.textView15);
            vReps2 = (TextView) v.findViewById(R.id.textView17);


            vShowMore = (TextView) v.findViewById(R.id.textView32);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("item clicked", vName.getText().toString());
                    Intent intent = new Intent(mContext.getApplicationContext(), EditExerciseActivity.class);
                    intent.putExtra("Card", card);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);

                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final CharSequence[] items = {"Yes", "No"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Delete " + vName.getText().toString());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            if (item == 0) {
                                mDatabase.removeValue();
                                cards.remove(item);
                                notifyDataSetChanged();
                                Toast.makeText(mContext, "Card Deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "Cancelled", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                    return true;
                }
            });


        }
    }
    class ViewHolder4 extends GeneralViewHolder {

        protected TextView vName;
        protected TextView vWeight;
        protected TextView vReps;
        protected TextView vWeight1;
        protected TextView vReps1;
        protected TextView vWeight2;
        protected TextView vReps2;
        protected TextView vWeight3;
        protected TextView vReps3;
        protected TextView vShowMore;
        protected Card card;
        protected int id;
        protected String id2;
        protected int category;
        protected RelativeLayout rl;


        public ViewHolder4(View v) {
            super(v);

            rl = (RelativeLayout) v.findViewById(R.id.rl);

            vName = (TextView) v.findViewById(R.id.editText);
            vWeight = (TextView) v.findViewById(R.id.textView7);
            vReps = (TextView) v.findViewById(R.id.textView9);

            vWeight1 = (TextView) v.findViewById(R.id.textView11);
            vReps1 = (TextView) v.findViewById(R.id.textView13);

            vWeight2 = (TextView) v.findViewById(R.id.textView15);
            vReps2 = (TextView) v.findViewById(R.id.textView17);

            vWeight3 = (TextView) v.findViewById(R.id.textView19);
            vReps3 = (TextView) v.findViewById(R.id.textView21);


            vShowMore = (TextView) v.findViewById(R.id.textView32);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("item clicked", vName.getText().toString());
                    Intent intent = new Intent(mContext.getApplicationContext(), EditExerciseActivity.class);
                    intent.putExtra("Card", card);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);

                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final CharSequence[] items = {"Yes", "No"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Delete " + vName.getText().toString());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            if (item == 0) {
                                mDatabase.removeValue();
                                cards.remove(item);
                                notifyDataSetChanged();
                                Toast.makeText(mContext, "Card Deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "Cancelled", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                    return true;
                }
            });


        }
    }
    class ViewHolder5 extends GeneralViewHolder {

        protected TextView vName;
        protected TextView vWeight;
        protected TextView vReps;
        protected TextView vWeight1;
        protected TextView vReps1;
        protected TextView vWeight2;
        protected TextView vReps2;
        protected TextView vWeight3;
        protected TextView vReps3;
        protected TextView vWeight4;
        protected TextView vReps4;
        protected TextView vShowMore;
        protected Card card;
        protected int id;
        protected String id2;
        protected int category;
        protected RelativeLayout rl;


        public ViewHolder5(View v) {
            super(v);

            rl = (RelativeLayout) v.findViewById(R.id.rl);

            vName = (TextView) v.findViewById(R.id.editText);
            vWeight = (TextView) v.findViewById(R.id.textView7);
            vReps = (TextView) v.findViewById(R.id.textView9);

            vWeight1 = (TextView) v.findViewById(R.id.textView11);
            vReps1 = (TextView) v.findViewById(R.id.textView13);

            vWeight2 = (TextView) v.findViewById(R.id.textView15);
            vReps2 = (TextView) v.findViewById(R.id.textView17);

            vWeight3 = (TextView) v.findViewById(R.id.textView19);
            vReps3 = (TextView) v.findViewById(R.id.textView21);

            vWeight4 = (TextView) v.findViewById(R.id.textView23);
            vReps4 = (TextView) v.findViewById(R.id.textView25);

            vShowMore = (TextView) v.findViewById(R.id.textView32);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("item clicked", vName.getText().toString());
                    Intent intent = new Intent(mContext.getApplicationContext(), EditExerciseActivity.class);
                    intent.putExtra("Card", card);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);

                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final CharSequence[] items = {"Yes", "No"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Delete " + vName.getText().toString());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            if (item == 0) {
                                mDatabase.removeValue();
                                cards.remove(item);
                                notifyDataSetChanged();
                                Toast.makeText(mContext, "Card Deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "Cancelled", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                    return true;
                }
            });


        }
    }

    class ViewHolder6 extends GeneralViewHolder {

        protected TextView vName;
        protected TextView vWeight;
        protected TextView vReps;
        protected TextView vShowMore;
        protected Card card;
        protected int id;
        protected String id2;
        protected int category;
        protected RelativeLayout rl;


        public ViewHolder6(View v) {
            super(v);

            rl = (RelativeLayout) v.findViewById(R.id.rl);

            vName = (TextView) v.findViewById(R.id.editText);
            vWeight = (TextView) v.findViewById(R.id.textView9);
            vReps = (TextView) v.findViewById(R.id.textView7);


            vShowMore = (TextView) v.findViewById(R.id.textView32);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("item clicked", vName.getText().toString());
                    Intent intent = new Intent(mContext.getApplicationContext(), EditExerciseActivity.class);
                    intent.putExtra("Card", card);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);

                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final CharSequence[] items = {"Yes", "No"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Delete " + vName.getText().toString());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            if (item == 0) {
                                mDatabase.removeValue();
                                cards.remove(item);
                                notifyDataSetChanged();
                                Toast.makeText(mContext, "Card Deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "Cancelled", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                    return true;
                }
            });


        }
    }

    class ViewHolder7 extends GeneralViewHolder {

        protected TextView vName;
        protected TextView vWeight;
        protected TextView vReps;
        protected TextView vWeight1;
        protected TextView vReps1;
        protected TextView vShowMore;
        protected Card card;
        protected int id;
        protected String id2;
        protected int category;
        protected RelativeLayout rl;


        public ViewHolder7(View v) {
            super(v);

            rl = (RelativeLayout) v.findViewById(R.id.rl);

            vName = (TextView) v.findViewById(R.id.editText);
            vWeight = (TextView) v.findViewById(R.id.textView9);
            vReps = (TextView) v.findViewById(R.id.textView7);

            vWeight1 = (TextView) v.findViewById(R.id.textView13);
            vReps1 = (TextView) v.findViewById(R.id.textView11);

            vShowMore = (TextView) v.findViewById(R.id.textView32);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("item clicked", vName.getText().toString());
                    Intent intent = new Intent(mContext.getApplicationContext(), EditExerciseActivity.class);
                    intent.putExtra("Card", card);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);

                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final CharSequence[] items = {"Yes", "No"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Delete " + vName.getText().toString());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            if (item == 0) {
                                mDatabase.removeValue();
                                cards.remove(item);
                                notifyDataSetChanged();
                                Toast.makeText(mContext, "Card Deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "Cancelled", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                    return true;
                }
            });


        }
    }

    class ViewHolder8 extends GeneralViewHolder {

        protected TextView vName;
        protected TextView vWeight;
        protected TextView vReps;
        protected TextView vWeight1;
        protected TextView vReps1;
        protected TextView vWeight2;
        protected TextView vReps2;
        protected TextView vShowMore;
        protected Card card;
        protected int id;
        protected String id2;
        protected int category;
        protected RelativeLayout rl;


        public ViewHolder8(View v) {
            super(v);

            rl = (RelativeLayout) v.findViewById(R.id.rl);

            vName = (TextView) v.findViewById(R.id.editText);
            vWeight = (TextView) v.findViewById(R.id.textView9);
            vReps = (TextView) v.findViewById(R.id.textView7);

            vWeight1 = (TextView) v.findViewById(R.id.textView13);
            vReps1 = (TextView) v.findViewById(R.id.textView11);

            vWeight2 = (TextView) v.findViewById(R.id.textView17);
            vReps2 = (TextView) v.findViewById(R.id.textView15);


            vShowMore = (TextView) v.findViewById(R.id.textView32);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("item clicked", vName.getText().toString());
                    Intent intent = new Intent(mContext.getApplicationContext(), EditExerciseActivity.class);
                    intent.putExtra("Card", card);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);

                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final CharSequence[] items = {"Yes", "No"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Delete " + vName.getText().toString());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            if (item == 0) {
                                mDatabase.removeValue();
                                cards.remove(item);
                                notifyDataSetChanged();
                                Toast.makeText(mContext, "Card Deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "Cancelled", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                    return true;
                }
            });


        }
    }
    class ViewHolder9 extends GeneralViewHolder {

        protected TextView vName;
        protected TextView vWeight;
        protected TextView vReps;
        protected TextView vWeight1;
        protected TextView vReps1;
        protected TextView vWeight2;
        protected TextView vReps2;
        protected TextView vWeight3;
        protected TextView vReps3;
        protected TextView vShowMore;
        protected Card card;
        protected int id;
        protected String id2;
        protected int category;
        protected RelativeLayout rl;


        public ViewHolder9(View v) {
            super(v);

            rl = (RelativeLayout) v.findViewById(R.id.rl);

            vName = (TextView) v.findViewById(R.id.editText);
            vWeight = (TextView) v.findViewById(R.id.textView9);
            vReps = (TextView) v.findViewById(R.id.textView7);

            vWeight1 = (TextView) v.findViewById(R.id.textView13);
            vReps1 = (TextView) v.findViewById(R.id.textView11);

            vWeight2 = (TextView) v.findViewById(R.id.textView17);
            vReps2 = (TextView) v.findViewById(R.id.textView15);

            vWeight3 = (TextView) v.findViewById(R.id.textView21);
            vReps3 = (TextView) v.findViewById(R.id.textView19);


            vShowMore = (TextView) v.findViewById(R.id.textView32);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("item clicked", vName.getText().toString());
                    Intent intent = new Intent(mContext.getApplicationContext(), EditExerciseActivity.class);
                    intent.putExtra("Card", card);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);

                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final CharSequence[] items = {"Yes", "No"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Delete " + vName.getText().toString());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            if (item == 0) {
                                mDatabase.removeValue();
                                cards.remove(item);
                                notifyDataSetChanged();
                                Toast.makeText(mContext, "Card Deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "Cancelled", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                    return true;
                }
            });


        }
    }
    class ViewHolder10 extends GeneralViewHolder {

        protected TextView vName;
        protected TextView vWeight;
        protected TextView vReps;
        protected TextView vWeight1;
        protected TextView vReps1;
        protected TextView vWeight2;
        protected TextView vReps2;
        protected TextView vWeight3;
        protected TextView vReps3;
        protected TextView vWeight4;
        protected TextView vReps4;
        protected TextView vShowMore;
        protected Card card;
        protected int id;
        protected String id2;
        protected int category;
        protected RelativeLayout rl;


        public ViewHolder10(View v) {
            super(v);

            rl = (RelativeLayout) v.findViewById(R.id.rl);

            vName = (TextView) v.findViewById(R.id.editText);
            vWeight = (TextView) v.findViewById(R.id.textView9);
            vReps = (TextView) v.findViewById(R.id.textView7);

            vWeight1 = (TextView) v.findViewById(R.id.textView13);
            vReps1 = (TextView) v.findViewById(R.id.textView11);

            vWeight2 = (TextView) v.findViewById(R.id.textView17);
            vReps2 = (TextView) v.findViewById(R.id.textView15);

            vWeight3 = (TextView) v.findViewById(R.id.textView21);
            vReps3 = (TextView) v.findViewById(R.id.textView19);

            vWeight4 = (TextView) v.findViewById(R.id.textView25);
            vReps4 = (TextView) v.findViewById(R.id.textView23);

            vShowMore = (TextView) v.findViewById(R.id.textView32);


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("item clicked", vName.getText().toString());
                    Intent intent = new Intent(mContext.getApplicationContext(), EditExerciseActivity.class);
                    intent.putExtra("Card", card);
                    intent.putExtra("id", id);
                    mContext.startActivity(intent);

                }
            });
            v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    final CharSequence[] items = {"Yes", "No"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Delete " + vName.getText().toString());
                    builder.setItems(items, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int item) {
                            if (item == 0) {
                                mDatabase.removeValue();
                                cards.remove(item);
                                notifyDataSetChanged();
                                Toast.makeText(mContext, "Card Deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(mContext, "Cancelled", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                    return true;
                }
            });


        }
    }
}
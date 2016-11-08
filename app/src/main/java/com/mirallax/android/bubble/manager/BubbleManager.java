package com.mirallax.android.bubble.manager;

import java.util.ArrayList;
import java.util.Random;

import android.os.Bundle;

import com.mirallax.android.bubble.sprite.BmpWrap;

public class BubbleManager {
    private int[] countBubbles;
    int bubblesLeft;
    ArrayList<BmpWrap> bubbles;

    public BubbleManager(ArrayList<BmpWrap> bubbles) {
        this.bubbles = bubbles;
        this.countBubbles = new int[bubbles.size()];
        this.bubblesLeft = 0;
    }

    public void saveState(Bundle map) {
        map.putInt("BubbleManager-bubblesLeft", bubblesLeft);
        map.putIntArray("BubbleManager-countBubbles", countBubbles);
    }

    public void restoreState(Bundle map) {
        bubblesLeft = map.getInt("BubbleManager-bubblesLeft");
        countBubbles = map.getIntArray("BubbleManager-countBubbles");
    }

    public void addBubble(BmpWrap bubble) {
        countBubbles[findBubble(bubble)]++;
        bubblesLeft++;
    }

    public void removeBubble(BmpWrap bubble) {
        countBubbles[findBubble(bubble)]--;
        bubblesLeft--;
    }

    public int countBubbles() {
        return bubblesLeft;
    }

    public int nextBubbleIndex(Random rand) {
        int select = rand.nextInt() % bubbles.size();

        if (select < 0) {
            select = -select;
        }

        int count = -1;
        int position = -1;

        while (count != select) {
            position++;

            if (position == bubbles.size()) {
                position = 0;
            }

            if (countBubbles[position] != 0) {
                count++;
            }
        }

        return position;
    }

    private int findBubble(BmpWrap bubble) {
        for (int i = 0; i < bubbles.size(); i++) {
            if (bubbles.get(i) == bubble) {
                return i;
            }
        }

        return -1;
    }
}

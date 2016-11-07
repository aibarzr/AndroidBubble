package com.mirallax.android.bubble.manager;

import java.util.Random;

import android.os.Bundle;

import com.mirallax.android.bubble.sprite.BmpWrap;

public class BubbleManager {
    int bubblesLeft;
    BmpWrap[] bubbles;
    int[] countBubbles;

    public BubbleManager(BmpWrap[] bubbles) {
        this.bubbles = bubbles;
        this.countBubbles = new int[bubbles.length];
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
        int select = rand.nextInt() % bubbles.length;

        if (select < 0) {
            select = -select;
        }

        int count = -1;
        int position = -1;

        while (count != select) {
            position++;

            if (position == bubbles.length) {
                position = 0;
            }

            if (countBubbles[position] != 0) {
                count++;
            }
        }

        return position;
    }

    private int findBubble(BmpWrap bubble) {
        for (int i = 0; i < bubbles.length; i++) {
            if (bubbles[i] == bubble) {
                return i;
            }
        }

        return -1;
    }
}

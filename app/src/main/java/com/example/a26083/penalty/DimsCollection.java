package com.example.a26083.penalty;

public class DimsCollection {

    private int dims_ld[] [] = {
            {-420, -200},
            {-360, -220},
            {-310, -240},
            {-260, -250},
            {-200, -310},
            {-180, -240},
            {-90, -230},
            {-40, -220},
            {0, -200},
            {-140, -200},

            {-130, -240},
            {-60, -220},
            {-160, -300},
            {-40, -200},
            {0, -220},
            {-60, -220},
    };

    private int dims_lm[] [] = {
            {-390, -320},
            {-340, -320},
            {-310, -330},
            {-240, -340},
            {-160, -370},
            {-140, -290},
            {-100, -280},
            {-50, -250},
            {0, -200},
            {-130, -240},

            {-200, -310},
            {-90, -230},
            {-40, -220},
            {-140, -200},
            {-150, -380},
            {-110, -320},
            {-80, -260},
            {-60, -220},
            {-160, -300},
            {0, -220},
            {-110, -330},
            {-60, -220}
    };

    private int dims_lt[] [] ={
            {-360, -510},
            {-310, -490},
            {-260, -470},
            {-210, -450},
            {-120, -440},
            {-150, -380},
            {-110, -320},
            {-80, -260},
            {-60, -220},
            {-160, -300},

            {-200, -310},
            {-90, -230},
            {-40, -220},
            {-160, -370},
            {-140, -290},
            {-100, -280},
            {-50, -250},
            {-40, -200},
            {-110, -330},
            {-60, -220},
            {-70, -300}
    };

    private int dims_md[] [] ={
            {-40, -200},
            {50, -200},
            {0, -220},
            {0, -310},
            {-70, -310},
            {-110, -330},
            {120, -330},
            {80, -310},
            {-30, -340},
            {30, -340},

            {-40, -220},
            {0, -200},
            {-50, -250},
            {-110, -320},
            {-60, -220},
            {0, -400},
            {50, -340},
            {-20, -320},
            {-20, -280},
            {-60, -220},
            {-70, -300},
            {40, -220},
            {100, -280},
            {50, -250},
            {110, -320},
            {60, -220}
    };

    private int dims_mt[] [] ={
            {0, -540},
            {-10, -510},
            {0, -470},
            {0, -400},
            {50, -340},
            {-20, -320},
            {-20, -280},
            {-60, -220},
            {-70, -300},

            {-90, -230},
            {-40, -220},
            {-100, -280},
            {-50, -250},
            {-80, -260},
            {-60, -220},
            {-40, -200},
            {0, -310},
            {-70, -310},
            {80, -310},
            {-30, -340},
            {30, -340}
    };

    private int dims_rd[] [] = {
            {420, -200},
            {360, -220},
            {310, -240},
            {260, -250},
            {200, -310},
            {180, -240},
            {90, -230},
            {40, -220},
            {0, -200},
            {140, -200},

            {50, -200},
            {0, -220},
            {130, -240},
            {60, -220},
            {160, -300}
    };

    private int dims_rm[] [] = {
            {390, -320},
            {340, -320},
            {310, -330},
            {240, -340},
            {160, -370},
            {140, -290},
            {100, -280},
            {50, -250},
            {0, -200},
            {130, -240},

            {0, -220},
            {120, -330},
            {200, -310},
            {180, -240},
            {90, -230},
            {40, -220},
            {140, -200},
            {150, -380},
            {110, -320},
            {80, -260},
            {60, -220},
            {160, -300}
    };

    private int dims_rt[] [] ={
            {360, -510},
            {310, -490},
            {260, -470},
            {210, -450},
            {120, -440},
            {150, -380},
            {110, -320},
            {80, -260},
            {60, -220},
            {160, -300},

            {50, -200},
            {120, -330},
            {200, -310},
            {90, -230},
            {160, -370},
            {140, -290},
            {100, -280}
    };

    private int left_kick [] [] ={
            // left save range
            {-360, -220},
            {-310, -240},
            {-260, -250},
            {-200, -310},
            {-180, -240},
            {-90, -230},
            {-390, -320},
            {-340, -320},
            {-310, -330},
            {-240, -340},
            {-160, -370},
            {-140, -290},
            {-100, -280},
            {-130, -240},
            {-360, -510},
            {-310, -490},
            {-260, -470},
            {-210, -450},
            {-120, -440},
            {-150, -380},
            {-110, -320},
            {-160, -300},
            {-110, -330},

            // will score
            {-470, -540},
            {-470, -460},
            {-470, -380},
            {-470, -300},
            {-470, -220},
            {-410, -250},
            {-360, -270},
            {-360, -380},
            {-360, -440},
            {-300, -420},
            {-300, -540},
            {-270, -540},
            {-120, -500},
            {-120, -540},
            {-90, -480},

            // will miss
            {-540, -220},
            {-540, -270},
            {-540, -320},
            {-540, -360},
            {-540, -410},
            {-540, -460},
            {-540, -510},
            {-540, -560},
            {-540, -610},
            {-490, -610},
            {-440, -610},
            {-390, -610},
            {-340, -610},
            {-290, -610},
            {-240, -610},
            {-190, -610},
            {-140, -610},
            {-90, -610}
    };

    private static int score_test [] [] ={

    };

    private int middle_kick [] [] = {
            // middle save range
            {0, -220},
            {0, -310},
            {-70, -310},
            {60, -220},
            {-30, -340},
            {30, -340},
            {-40, -220},
            {-50, -250},
            {0, -540},
            {-10, -510},
            {0, -470},
            {0, -400},
            {50, -340},
            {-20, -320},
            {-20, -280},
            {-60, -220},
            {-70, -300},
            {-80, -260},
            {-60, -220},
            {40, -220},
            {80, -310},
            {50, -250},
            {80, -260},

            // will score
            {-80, -380},
            {-60, -400},
            {-50, -430},
            {-50, -460},
            {-80, -480},
            {-70, -540},
            {30, -490},
            {30, -540},
            {60, -540},
            {70, -500},
            {80, -380},
            {80, -480},

            // will miss
            {-70, -610},
            {-60, -610},
            {-50, -610},
            {-40, -610},
            {-30, -610},
            {-20, -610},
            {-10, -610},
            {0, -610},
            {70, -610},
            {60, -610},
            {50, -610},
            {40, -610},
            {30, -610},
            {20, -610},
            {10, -610}
    };

    private int right_kick [] [] = {
            // right save range
            {360, -220},
            {310, -240},
            {260, -250},
            {200, -310},
            {180, -240},
            {90, -230},
            {120, -330},
            {390, -320},
            {340, -320},
            {310, -330},
            {240, -340},
            {160, -370},
            {140, -290},
            {100, -280},
            {130, -240},
            {360, -510},
            {310, -490},
            {260, -470},
            {210, -450},
            {120, -440},
            {150, -380},
            {110, -320},
            {160, -300},

            // will score
            {470, -540},
            {470, -460},
            {470, -380},
            {470, -300},
            {470, -220},
            {410, -250},
            {360, -270},
            {360, -380},
            {360, -440},
            {300, -420},
            {300, -540},
            {270, -540},
            {120, -500},
            {120, -540},
            {90, -480},

            // will miss
            {540, -220},
            {540, -270},
            {540, -320},
            {540, -360},
            {540, -410},
            {540, -460},
            {540, -510},
            {540, -560},
            {540, -610},
            {490, -610},
            {440, -610},
            {390, -610},
            {340, -610},
            {290, -610},
            {240, -610},
            {190, -610},
            {140, -610},
            {90, -610}
    };

    private int saves_end[] [] = {
            {0, -610},
            {235, -610},
            {470, -610},
            {-235, -610},
            {-470, -610},
            {-400, 0},
            {400, 0},
            {540, -220},
            {-540, -220}
    };

    private int Legend_shoot[] = {
            0,
            0,
            1,
            1,
            1,
            1,
            1,
            1,
            1,
            2
    };

    private int Super_shoot[] = {
            0,
            0,
            0,
            0,
            1,
            1,
            1,
            1,
            2,
            2
    };

    private int Star_shoot[] = {
            0,
            0,
            0,
            0,
            1,
            1,
            1,
            2,
            2,
            2
    };

    private int Wizard_shoot[] = {
            0,
            0,
            1,
            2,
            2,
            2,
            2,
            2,
            2,
            2
    };

    public int[][] getDims_ld()
    {
        return dims_ld;
    }

    public int[][] getDims_lm() {
        return dims_lm;
    }

    public int[][] getDims_lt() {
        return dims_lt;
    }

    public int[][] getDims_md() {
        return dims_md;
    }

    public int[][] getDims_mt() {
        return dims_mt;
    }

    public int[][] getDims_rd() {
        return dims_rd;
    }

    public int[][] getDims_rm() {
        return dims_rm;
    }

    public int[][] getDims_rt() {
        return dims_rt;
    }

    public int[][] getLeft_kick()
    {
        return left_kick;
    }

    public int[][] getMiddle_kick()
    {
        return middle_kick;
    }

    public int[][] getRight_kick()
    {
        return right_kick;
    }

    public int[][] getSaves_end() {
        return saves_end;
    }

    public int[] getLegend_shoot() {
        return Legend_shoot;
    }

    public int[] getSuper_shoot() {
        return Super_shoot;
    }

    public int[] getStar_shoot() {
        return Star_shoot;
    }

    public int[] getWizard_shoot() {
        return Wizard_shoot;
    }

    public boolean in_range(int x, int y, int[][] dims_save)
    {
        for(int i = 0; i < dims_save.length; i++)
        {
            if(dims_save[i][0] == x && dims_save[i][1] == y)
            {
                return true;
            }
        }

        return false;
    }

}
package module.imageutils.imagefusion;

/**
 * Created by kshea12 on 3/22/16.
 */
public class ImageFusion {

    public static double[][] averageFusion(double[][] first, double[][] second){
        int w = first[0].length;
        int h = first.length;
        double[][] fused = new double[h][w];
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++) {
                fused[i][j] = (first[i][j] + second[i][j]) / 2;
            }
        }
        return fused;
    }

    public static double[][] maxFusion(double[][] first, double[][] second){
        int w = first[0].length;
        int h = first.length;
        double[][] fused = new double[h][w];
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++) {
                if(first[i][j] > second[i][j])
                    fused[i][j] = first[i][j];
                else
                    fused[i][j] = second[i][j];
            }
        }
        return fused;
    }
}

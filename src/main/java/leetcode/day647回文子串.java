package leetcode;

/**
 * @author kfzx-liuc02
 * @version 1.0
 * @date 2023/3/14 11:22
 * @Description
 */
public class day647回文子串 {
    int sumPrice=0;
    int maxPrice=0;
    public int getMax(int[] price ,int totalPrice){
        getMax(price,totalPrice,0);
        return maxPrice;
    }
    public void getMax(int[] price ,int totalPrice,int index){
        if(sumPrice>totalPrice) return ;
        maxPrice=Math.max(maxPrice,sumPrice);
        for(int i=index;i<price.length;i++){
            sumPrice+=price[i];
            getMax(price,totalPrice,index+1);
            sumPrice-=price[i];
        }
    }

//    public void getMax(int[] price ,int totalPrice,int index){
//        for(int i=index;i<price.length;i++){
//            if(sumPrice+price[i]<=totalPrice){
//                maxPrice=Math.max(maxPrice,sumPrice+price[i]);
//                getMax(price,totalPrice,index+1);
//            }
//        }
//
//    }
    public  int maxPrice(int[] price ,int[] number,int totalPrice){
        dfs(price,number,totalPrice);
        return maxPrice;
    }

    private void dfs(int[] price, int[] number, int totalPrice) {
        if(sumPrice>totalPrice) return ;
        maxPrice=Math.max(maxPrice,sumPrice);
        for(int i=0;i<price.length;i++){
            if(number[i]==0)
                continue;
            sumPrice+=price[i];
            number[i]--;
            getMax(price,totalPrice);
            number[i]++;
            sumPrice-=price[i];
        }
    }
//背包问题递归回溯（未优化）
    int sumWeight=0;
    public  int maxPrice2(int[] price ,int[] weight,int totalWeight){
        dfs2(price,weight,totalWeight,0);
        return maxPrice;
    }

    private void dfs2(int[] price, int[] weight, int totalWeight,int index) {
        if(sumWeight>totalWeight) return ;
        maxPrice=Math.max(maxPrice,sumPrice);
        for(int i=index;i<price.length;i++){
            sumWeight+=weight[i];
            sumPrice+=price[i];
            dfs2(price,weight,totalWeight,index+1);
            sumPrice-=price[i];
            sumWeight-=weight[i];
        }
    }
}

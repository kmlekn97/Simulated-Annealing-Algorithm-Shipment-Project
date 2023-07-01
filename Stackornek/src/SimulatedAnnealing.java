import java.util.ArrayList;
import java.util.Random;

public class SimulatedAnnealing {
    private ArrayList<Mesafe> mesafe=DataBaseManager.Mesafeler.MesafeleriListele(-1,1);
    private int count= (int) Math.sqrt(mesafe.size());
    private  float [][] distance =new float[count][count];
    private  int [] initialSolution =new int[count];

    private  int [] bestSolution =new int[count];

    private int [] ids=new int[count];

    public SimulatedAnnealing(float T,float ratio){
        Neghbours n=new Neghbours();
        double best = 99999;
        double[] bestsol = new double[count];
        bestsol[0]=best;
        createmesafe();
        //initialSolution=getInitialSolution(distance,count);
        initialSolution=n.createRandom(count);
        for (int i=0;i<initialSolution.length;i++)
        {
            initialSolution[i]=initialSolution[i]+1;
        }
        float initialFit=calculatetotalDisstance(initialSolution);
        while (T>1)
        {
            int[] neighborhoodSolution=n.singleSwapMove(initialSolution);
            float neighboorFit=calculatetotalDisstance(neighborhoodSolution);
            if (neighboorFit < initialFit)
            {
                initialSolution=neighborhoodSolution;
                initialFit=neighboorFit;
            }
            else
            {
                double p=Math.exp(-(neighboorFit-initialFit)/T);
                Random rnd=new Random();
                if (rnd.nextFloat(0,1)<p)
                {
                    initialSolution=neighborhoodSolution;
                    initialFit=neighboorFit;
                }
            }
            if (initialFit<best)
            {
                best=initialFit;
                for(int i=0; i<initialSolution.length;i++){
                    bestSolution[i] = initialSolution[i];
                }
            }
            T=T*ratio;
        }
        displayarray(bestSolution);
        findSolutionId();
        displayarray(ids);
        System.out.println("\nTotal:"+calculatetotalDisstance(bestSolution));
    }
    public void createmesafe(){
        for (int i=0;i<count;i++){
            for (int j=0;j<count;j++){
                if (i==j){
                    distance[i][j]=99999;
                }
            }
        }
        for (Mesafe item:mesafe)
        {
            if ((item.getBaslangic_sehir_no() - 1!=item.getVaris_sehir_no() - 1) && item.getMesafe_durum()==0) {
                distance[item.getBaslangic_sehir_no() - 1][item.getVaris_sehir_no() - 1] = item.getMesafe_uzakliği();
            }
        }
    }
    public float[][] cleardistence(float array[][],int bassehir){
        for (int i=0;i<array[0].length;i++)
        {
           array[i][bassehir]=99999;
        }
        return array;
    }

    public float findMin(float array[]){
        float min=array[0];
        for (int i=0;i<array.length;i++)
        {
            if (array[i]<min)
                min=array[i];
        }
        return min;
    }

    public int[] getInitialSolution(float mesafe[][],int nbcities){
        int bassehir= 0;
        int ziyaretsehri=0;
        int counter=1;
        int [] sira=new int[nbcities];
        sira[0]=bassehir;
        mesafe=cleardistence(mesafe,bassehir);
        while (counter<nbcities)
        {
            for (int i=0;i<mesafe[0].length;i++)
            {
                if (mesafe[bassehir][i]== findMin(mesafe[bassehir]))
                {
                    ziyaretsehri=i;

                }
            }
            sira[counter]=ziyaretsehri;
            bassehir=ziyaretsehri;
            mesafe=cleardistence(mesafe,bassehir);
            counter++;
        }
        for (int i=0;i<sira.length;i++)
        {
            sira[i]=sira[i]+1;
        }
        return sira;
    }
    public float calculatetotalDisstance(int sira[])
    {
        float total=0;
        for (int i=0;i<sira.length;i++)
        {
            if (i!=sira.length-1)
            {
                ArrayList<Mesafe> mesafe=DataBaseManager.Mesafeler.MesafeleriVarisagöreListele(sira[i], sira[i+1],1);
                for (Mesafe item:mesafe)
                {
                    total+=item.getMesafe_uzakliği();
                }
            }
            else
            {
                ArrayList<Mesafe> mesafe=DataBaseManager.Mesafeler.MesafeleriVarisagöreListele(sira[i], sira[0],1);
                for (Mesafe item:mesafe)
                {
                    total+=item.getMesafe_uzakliği();
                }
            }
        }
        return total;
    }
    public void displayarray(int array[])
    {
        for (int i=0;i<count;i++){
            System.out.print(" "+array[i]);
        }
    }

    public  int []  getbestSolutionids(){
        return ids;
    }
    public void findSolutionId()
    {
        for (int i=0;i<ids.length;i++)
        {
            for (Mesafe item:mesafe)
            {
                if (i!=ids.length-1)
                {
                    if ((item.getBaslangic_sehir_no()==bestSolution[i] && item.getVaris_sehir_no()==bestSolution[i+1])) {
                        ids[i]=item.getMesafeler_id();
                    }
                }
                else {
                    if ((item.getBaslangic_sehir_no()==bestSolution[i] && item.getVaris_sehir_no()==bestSolution[0])) {
                        ids[i]=item.getMesafeler_id();
                    }
                }
            }
        }
    }
}

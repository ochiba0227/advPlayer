public class myplayer{    
    private static Thread othread;
    private static playmethod mthread;
    private static outside opanel;
    
    public static void Setothread(Thread thread){
    	othread=thread;
    }
    public static void Setmthread(playmethod thread){
    	mthread=thread;
    }
    
    public static Thread Getothread(){
    	return othread;
    }
    public static playmethod Getmthread(){
    	return mthread;
    }
    public static outside Getopanel(){
    	return opanel;
    }
    
    public static void main(String[] args) {
    	opanel = new outside();
    	othread = new Thread(opanel);
        mthread = new playmethod();
        
        othread.start();
    }
}
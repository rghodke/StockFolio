package com.clearbox.stockfolio;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RonTest {


    public static void hello() {
        List<String> files = new ArrayList<>();
        files.add("3746");
        files.add("3748");
        files.add("3749");
        files.add("3750");
        files.add("3751");
        files.add("3754");
        files.add("3756");
        files.add("3761");
        files.add("3768");
        files.add("3769");
        files.add("3771");
        files.add("3776");
        files.add("3775");
        files.add("3778");
        files.add("3780");
        files.add("3798");
        files.add("3807");
        files.add("3818");
        files.add("3819");
        files.add("3820");
        files.add("3823");
        files.add("3824");
        files.add("3826");
        files.add("3833");
        files.add("3834");
        files.add("3836");
        files.add("3838");
        files.add("3851");
        files.add("3852");
        files.add("3854");
        files.add("3855");
        files.add("3856");
        files.add("3866");
        files.add("3868");
        files.add("3870");
        files.add("3872");
        files.add("3873");
        files.add("3874");
        files.add("3877");
        files.add("3880");
        files.add("3881");
        files.add("3885");
        files.add("3887");
        files.add("3897");
        files.add("3899");
        files.add("3915");
        files.add("3919");
        files.add("3926");
        files.add("3929");
        files.add("3930");
        files.add("3934");
        files.add("3935");
        files.add("3938");
        files.add("3939");
        files.add("3943");
        files.add("3948");
        files.add("3950");
        files.add("3951");
        files.add("3952");
        files.add("3959");
        files.add("3960");
        files.add("3962");
        files.add("3966");
        files.add("3967");
        files.add("3969");
        files.add("3971");
        files.add("3972");
        files.add("3974");
        files.add("3975");
        files.add("3977");
        files.add("3978");
        files.add("3994");
        files.add("4002");
        files.add("4005");
        File dir = new File("D:\\3-6\\");
        for (String s : files) {
            for(File x : dir.listFiles()) {
                if (x.getName().contains(s)) {
                    x.renameTo(new File("D:\\3-6\\nf\\"+ x.getName()));
                }
            }
        }

    }

}

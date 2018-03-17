package com.spsa.sonar;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.Extension;
import org.sonar.api.SonarPlugin;

public class CleanCodePlugin extends SonarPlugin {

    @Override
    public List<Class<? extends Extension>> getExtensions() {
        return Arrays.asList(CleanCodeRulesDefinition.class, CleanCodeFileCheckRegistrar.class);
    }

}

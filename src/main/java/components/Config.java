package components;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import machine.factory.MachineFactory;
import machine.models.Machine;
import machine.models.State;
import states.A1;
import states.B1;
import states.EntryState;

@Configuration
@ComponentScan({"States"})
public class Config {

	@Bean
	public MachineFactory getMachineFactory() {
		return new MachineFactory();
	}

	@Bean
	public Machine getMachine(){		
		String packageName = "states";
		Set<Class<? extends State>> statesClasses = this.getClassesInPackage(packageName);
		return getMachineFactory().getMachine(statesClasses, EntryState.class);
	}
	
	@SuppressWarnings("unchecked")
	private Set<Class<? extends State>> getClassesInPackage(String packageName){
		Set<Class<? extends State>> classes = new HashSet<>();
		URL root = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));
		// Filter .class files.
		File[] files = new File(root.getFile()).listFiles(new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.endsWith(".class");
		    }
		});
		// Find classes implementing ICommand.
		for (File file : files) {
		    String className = file.getName().replaceAll(".class$", "");
		    Class<?> clazz;
			try {
				clazz = Class.forName(packageName + "." + className);
			    if (State.class.isAssignableFrom(clazz)) {
			    	classes.add((Class<? extends State>) clazz);
			    }
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return classes;
	}

}

package it.spootify.spootify.web.rest.config;

import java.util.HashSet;
import java.util.Set;

import it.spootify.spootify.web.rest.resources.AlbumResource;
import it.spootify.spootify.web.rest.resources.ArtistaResource;
import it.spootify.spootify.web.rest.resources.BranoResource;
import it.spootify.spootify.web.rest.resources.PlaylistResource;
import it.spootify.spootify.web.rest.resources.RiproduzioneResource;
import it.spootify.spootify.web.rest.resources.RuoloResource;
import it.spootify.spootify.web.rest.resources.SessioneResource;
import it.spootify.spootify.web.rest.resources.UtenteResource;

public class RestServicesConfig {
	 public Set<Class<?>> getClasses() {
	        Set<Class<?>> classes = new HashSet<Class<?>>();
	        classes.add(UtenteResource.class);
	        classes.add(AlbumResource.class);
	        classes.add(BranoResource.class);
	        classes.add(PlaylistResource.class);
	        classes.add(SessioneResource.class);
	        classes.add(ArtistaResource.class);
	        classes.add(RuoloResource.class);
	        classes.add(RiproduzioneResource.class);
	        return classes;
		}
}

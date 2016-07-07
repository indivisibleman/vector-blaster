package polysprite;

import collision.Vector;

public class Entity {
	Vector position;
	Vector velocity;

	double orientation;
	double spin;

	RenderComponent renderer;
	PhysicsComponent physics;
	InputComponent input;

	private Entity() {
		// Private constructor forcing use of static Builder class
	}

	public static Builder physics(PhysicsComponent physics) {
		return Entity.physics(physics);
	}

	@FunctionalInterface
	public interface IPhysics {
		IRenderer physics(PhysicsComponent physics);
	}

	@FunctionalInterface
	public interface IRenderer {
		IInput renderer(RenderComponent renderer);
	}

	@FunctionalInterface
	public interface IInput {
		IBuild input(InputComponent input);
	}

	@FunctionalInterface
	public interface IBuild {
		Entity build();
	}

	public static class Builder implements IPhysics, IRenderer, IInput, IBuild {
		private Entity instance = new Entity();

		public Builder() {
		}

		@Override
		public IRenderer physics(PhysicsComponent physics) {
			instance.physics = physics;
			return this;
		}

		@Override
		public IInput renderer(RenderComponent renderer) {
			instance.renderer = renderer;
			return this;
		}

		@Override
		public IBuild input(InputComponent input) {
			instance.input = input;
			return this;
		}

		@Override
		public Entity build() {
			return instance;
		}
	}
}

unlink .git/hooks/pre-push || true
ln -s ../../git_hooks/pre-push.sh .git/hooks/pre-push

brew install pre-commit
pre-commit install
